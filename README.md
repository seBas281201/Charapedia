# 📚 Charapedia

Charapedia es una aplicación Android enfocada en la exploración y descubrimiento de personajes de algunas series de anime mediante el consumo de la API pública de Jikan. El proyecto está siendo desarrollado utilizando tecnologías modernas de Android y siguiendo buenas prácticas de arquitectura, diseño de interfaces y desarrollo de software.

> ⚠️ **Estado del Proyecto: En Desarrollo Activo**
>
> Este proyecto aún se encuentra en fase de desarrollo y no debe considerarse una versión final.

---

## ✨ Características

### Funcionalidades Actuales

* Consulta de personajes de diferentes series de anime.
* Visualización de información detallada de personajes.
* Consumo de múltiples APIs públicas relacionadas con anime.
* Interfaz moderna desarrollada con Jetpack Compose.
* Implementación de Material Design 3.
* Gestión de estados mediante ViewModels y Kotlin Coroutines.
* Inyección de dependencias con Hilt.
* Consumo de servicios REST mediante Retrofit.
* Soporte offline.
* Persistencia local con Room.

### Funcionalidades Planeadas

* Búsqueda avanzada de personajes.
* Configuración de preferencias de usuario.
* Mejoras visuales y animaciones avanzadas.
* Integración con nuevas fuentes de información.

---

## 🏗️ Arquitectura

El proyecto sigue el patrón de arquitectura **MVVM (Model - View - ViewModel)** con el objetivo de mantener una separación clara de responsabilidades y facilitar la escalabilidad del código.

```text
app
│
├── data
│   ├── remote
│   ├── repository
│   └── models
│
├── di
│
├── ui
│   ├── screens
│   ├── components
│   ├── navigation
│   ├── theme
│   └── viewmodels
│
└── utilities
```

### Principios Aplicados

* Responsabilidad Única (SRP)
* Separación de Responsabilidades
* Inyección de Dependencias
* Flujo Unidireccional de Datos
* Gestión Reactiva del Estado
* Componentes Reutilizables

---

## 🛠️ Tecnologías Utilizadas

### Lenguaje

* Kotlin

### Interfaz de Usuario

* Jetpack Compose
* Material Design 3

### Arquitectura

* MVVM
* StateFlow
* Kotlin Coroutines

### Inyección de Dependencias

* Hilt

### Networking

* Retrofit
* Gson

### Persistencia de Datos

* Room Database
* DataStore *(planificado)*

### Carga de Imágenes

* Coil

### Animaciones

* Lottie

### Navegación

* Navigation Compose

---

## 🚀 Instalación y Ejecución

### Requisitos Previos

Asegúrate de tener instalado:

* Android Studio (última versión estable)
* JDK 17 o superior
* Android SDK
* Git

### Clonar el Repositorio

```bash
git clone https://github.com/seBas281201/Charapedia.git
```

### Abrir el Proyecto

```bash
cd Charapedia
```

Abre la carpeta del proyecto en Android Studio y permite que Gradle sincronice todas las dependencias.

### Ejecutar la Aplicación

1. Conecta un dispositivo Android o inicia un emulador.
2. Compila el proyecto.
3. Ejecuta la aplicación.

---

## 🌿 Flujo de Trabajo Git

El proyecto utiliza una estrategia basada en ramas para mantener un desarrollo organizado.

### Ramas Principales

```text
main
│
└── Versiones estables

develop
│
└── Desarrollo activo
```

### Ramas de Funcionalidades

```text
feature/localPersistence
```

Cada nueva funcionalidad se desarrolla en una rama independiente y posteriormente se integra a `develop`.

---

## 📈 Hoja de Ruta

### Fase 1 – Funcionalidades Base

* [x] Configuración inicial del proyecto
* [x] Integración con APIs
* [x] Listado de personajes
* [x] Pantalla de detalles
* [x] Sistema de navegación
* [x] Implementación de Material 3

### Fase 2 – Persistencia de Datos

* [x] Integración con Room
* [x] Soporte offline

### Fase 3 – Experiencia de Usuario

* [ ] Sistema de búsqueda
* [ ] Mejoras en estados de carga
* [ ] Mejor manejo de errores

### Fase 4 – Optimización

* [ ] Paginación
* [ ] Optimización de rendimiento
* [ ] Refactorización de código
* [ ] Mejoras visuales

### Fase 5 – Lanzamiento

* [ ] Versión Beta
* [ ] Release Candidate
* [ ] Versión 1.0.0

---

## 📸 Capturas de Pantalla

Las capturas serán agregadas a medida que el proyecto avance.

```text
docs/
└── screenshots/
```

---

## 🤝 Contribuciones

Actualmente, Charapedia es un proyecto personal enfocado en el aprendizaje, la práctica de tecnologías modernas de Android y la construcción de portafolio profesional.

Las sugerencias, observaciones y recomendaciones son siempre bienvenidas.

---

## 📄 Licencia

Este proyecto se distribuye con fines educativos, de aprendizaje y demostración técnica.

---

## 👨‍💻 Autor

**Sebastián Navarro**

Desarrollador Android Junior especializado en Kotlin, Jetpack Compose y arquitecturas modernas para aplicaciones móviles.

### Objetivos del Proyecto

* Fortalecer conocimientos en desarrollo Android moderno.
* Aplicar buenas prácticas de arquitectura y diseño.
* Construir un portafolio técnico sólido.
* Explorar integración con APIs públicas.
* Desarrollar una aplicación escalable y mantenible.
