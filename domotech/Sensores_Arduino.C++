#include <WiFi.h>
#include <MySQL_Connection.h>
#include <MySQL_Cursor.h>
#include <MySQL_Encrypt_Sha1.h>
#include <MySQL_Packet.h>

// WiFi
const char* ssid = "Henar";
const char* password = "T0rtu19g@";

// MySQL
IPAddress server_ip(195, 235, 211, 197);
uint16_t server_port = 3306;
char user[] = "pii2_Domotech";
char password_sql[] = "secure_password";
char database[] = "pii2_Domotech";

WiFiClient client;
MySQL_Connection conn(&client);

// Pines de sensores
#define LM35_PIN 34
#define LDR_PIN 35
#define PIR_PIN 5
#define LED_PIN 4


// IDs de sensores en la base de datos
const int SENSOR_ID_TEMPERATURA = 5;
const int SENSOR_ID_LUZ         = 6;
const int SENSOR_ID_MOVIMIENTO  = 7;
 

// Temporización
const unsigned long INTERVALO_MS = 10000;
unsigned long t0 = 0;

// Inicialización de sensores
void configurarSensores() {
  pinMode(LED_PIN, OUTPUT);
  digitalWrite(LED_PIN, LOW);
  pinMode(LM35_PIN, INPUT);
  pinMode(LDR_PIN, INPUT);
  pinMode(PIR_PIN, INPUT);
}

// Lectura de temperatura
float leerTemperaturaLM35() {
  int raw = analogRead(LM35_PIN);
  float voltage = raw * (3.3 / 4095.0);
  float temperatura = voltage * 100.0;

  Serial.print("Temperatura leída: ");
  Serial.print(temperatura);
  Serial.println(" °C");

  return temperatura;
}

// Lectura de luz (LDR)
int leerLuzLDR() {
  int valorLuz = analogRead(LDR_PIN);
  Serial.print("Luz (ADC): ");
  Serial.println(valorLuz);
  return valorLuz;
}

// Lectura de movimiento
int leerMovimientoPIR() {
  int estado = digitalRead(PIR_PIN);
  if (estado == HIGH) {
    Serial.println("Movimiento detectado");
    digitalWrite(LED_PIN, HIGH);
    return 1;
  } else {
    Serial.println("Sin movimiento");
    digitalWrite(LED_PIN, LOW); 
    return 0;
  }
}

// Envío de datos al servidor
void actualizarSensor(int sensor_id, float valor) {
  if (!conn.connected()) {
    Serial.println(" Conectando a base de datos...");
    if (!conn.connect(server_ip, server_port, user, password_sql)) {
      Serial.println("ERROR: No se pudo conectar a MySQL.");
      return;
    }
  }

  char query[256];
  snprintf(query, sizeof(query),
    "INSERT INTO %s.sensores (id_sensor, dato) VALUES (%d, %.2f) ON DUPLICATE KEY UPDATE dato = %.2f;",
    database, sensor_id, valor, valor);

  Serial.print(" SQL → ");
  Serial.println(query);

  MySQL_Cursor* cur = new MySQL_Cursor(&conn);
  if (cur->execute(query)) {
    Serial.println("Dato actualizado correctamente.");
  } else {
    Serial.println(" Error al ejecutar consulta.");
  }
  delete cur;
  conn.close();
}

void setup() {
  
  Serial.begin(115200);
  Serial.println("Conectando a WiFi...");

  WiFi.begin(ssid, password);
  unsigned long startAttemptTime = millis();

  while (WiFi.status() != WL_CONNECTED && millis() - startAttemptTime < 20000) {
    Serial.print(".");
    delay(500);
  }

  if (WiFi.status() == WL_CONNECTED) {
    Serial.println("\n ¡WiFi conectado!");
    Serial.print("IP asignada: ");
    Serial.println(WiFi.localIP());
  } else {
    Serial.println("\nNo se pudo conectar a WiFi. Reiniciando...");
    delay(2000);
    ESP.restart();
  }

  configurarSensores();
  t0 = millis();
}

void loop() {
  if (millis() - t0 >= INTERVALO_MS) {
    t0 = millis();

    float temperatura = leerTemperaturaLM35();
    int luz = leerLuzLDR();
    int movimiento = leerMovimientoPIR();

    Serial.println("Enviando datos a la base de datos...");
    actualizarSensor(SENSOR_ID_TEMPERATURA, temperatura);
    actualizarSensor(SENSOR_ID_LUZ, luz);
    actualizarSensor(SENSOR_ID_MOVIMIENTO, movimiento);
    
  }

 delay(100);
}