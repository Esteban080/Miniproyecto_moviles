# Miniproyecto Moviles: Reminder App

Esteban Alexander Revelo Salazar 2067507

# 📝 ReminderApp

**ReminderApp** es una aplicación móvil creada con Kotlin y Jetpack Compose, pensada para ayudar a las personas que suelen olvidar sus tareas, citas médicas, eventos importantes o pendientes diarios. Nace de la necesidad común de contar con una herramienta simple pero funcional que permita **crear, guardar, visualizar, fotografiar y recibir notificaciones de recordatorios importantes**.

## 🎯 ¿Para qué se creó esta app?

Muchas personas olvidan fácilmente actividades importantes como:
- Hacer tareas universitarias o escolares
- Asistir a citas médicas
- Comprar algo pendiente
- Realizar trámites personales

Con esta aplicación se busca brindar una solución sencilla para **registrar recordatorios personalizados** y **recibir notificaciones** justo en el momento programado.

---

## 📁 Estructura de clases

| Archivo | Descripción |
|--------|-------------|
| **MainActivity.kt** | Es la clase principal de la app. Muestra la lista de recordatorios guardados, permite eliminarlos, muestra el formulario para agregar nuevos recordatorios y gestiona las notificaciones. |
| **AddReminderScreen.kt** | Contiene el formulario que permite al usuario agregar un nuevo recordatorio con título, descripción, fecha y la opción de tomar una foto desde la cámara. |
| **Reminder.kt** | Es la clase modelo de datos (`data class`) que representa un recordatorio. Contiene campos como `title`, `description`, `date`, `imagePath`, entre otros. |
| **ReminderReceiver.kt** | Es un `BroadcastReceiver` que recibe la señal de alarma programada y muestra la notificación del recordatorio cuando es el momento adecuado. |
| **ReminderStorage.kt** | Se encarga de guardar y cargar los recordatorios en un archivo local en formato JSON. Actúa como una base de datos simple persistente. |
| **ui.theme** | Carpeta que contiene los temas de la app (colores, tipografías, estilos). |

---

## ✅ Funcionalidades implementadas

- Crear recordatorios con título, descripción y fecha.
- Adjuntar una foto tomada con la cámara para cada recordatorio.
- Mostrar todos los recordatorios en una lista con sus detalles.
- Recibir notificaciones en la fecha y hora programada.
- Eliminar recordatorios y cancelar sus notificaciones.
- Diseño moderno usando Jetpack Compose.

---

## 🛠️ Tecnologías usadas

- **Kotlin**
- **Jetpack Compose**
- **AlarmManager** y **BroadcastReceiver** (para notificaciones)
- **Coil** (para mostrar imágenes)
- **JSON** para almacenamiento local

---

### 📲 Instalación por USB
Para probar esta aplicación en un dispositivo físico:

1. Activa el **modo desarrollador** en tu teléfono Android.
2. Habilita la opción **Depuración por USB**.
3. Conecta tu teléfono por USB al computador.
4. Ejecuta el proyecto desde Android Studio haciendo clic en **Run ▶** y seleccionando tu dispositivo físico.

> 💡 Esta app no está publicada en la Play Store. Por ahora, solo se instala localmente desde Android Studio.

### ⚠️ Permisos necesarios
La aplicación requiere los siguientes permisos para funcionar correctamente:

- **Permiso de cámara:** para tomar fotos con la cámara del dispositivo.
- **Permiso de notificaciones:** para poder mostrar alertas cuando se acerque la fecha del recordatorio.

> 📌 Al iniciar por primera vez, asegúrate de **aceptar los permisos solicitados**. Si se rechazan, puedes activarlos manualmente desde la configuración del dispositivo.

---