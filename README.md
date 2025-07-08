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
