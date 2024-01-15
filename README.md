# APP TASK LIST
# Qué es este proyecto?
Una App para crear tareas con dos sesiones completas y pendientes.,

Proyecto
[https://i.imgur.com/ILRKvafl.png](https://i.imgur.com/396ZlMZl.png).

![](https://i.imgur.com/YS54hA9h.png)

# Características principales
1. Kotlin
1. Arquitectura MVI
1. Jetpack Compose
1. MutableStateOf
1. UIEvent
1.   Realm - Realm es una base de datos móvil que se ejecuta directamente en teléfonos, tabletas o dispositivos portátiles. Este repositorio contiene el código fuente de la versión Java de Realm, que actualmente solo se ejecuta en Android.
1. Koin
1. Navigation Compose
1. Corrutinas
1. Coil
1. ArrowKt 
    - Toggle between themes

# Funciones de composición
1. Snackbars
3. Theming /Modo Oscuro
4. Fonts
5. Colors
    - creating
7. ConstraintLayout
8. Rows
9. Columns
10. Scaffold
11. AppBar
12. Circular Progress Indicator

# Pruebas
1. Junit

#  Flow?
1. Flow
    1. Flow es genial. es una característica de Kotlin coroutines que proporciona una forma asincrónica y reactiva de trabajar con secuencias de datos.
       El método getListRecipeUseCase devuelve un Flow ya que se realiza una operación collect en el resultado. Esto indica que probablemente getListRecipeUseCase retorna un flujo de datos asincrónico que el ViewModel está consumiendo.
       1.(https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/).
1. StateFlow Semántica de Estado:
    1. StateFlow  está diseñado específicamente para representar un estado mutable y proporcionar un flujo de eventos que notifica a los suscriptores cuando el estado cambia. Esto es útil cuando necesitas mantener y compartir un estado mutable en toda tu aplicación. (https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState) in viewmodels.
1. StateFlow Semántica de Estado:
    1. SharedFlow  está diseñado para emitir eventos o notificaciones a sus suscriptores. Es adecuado cuando necesitas comunicar eventos que no necesariamente están relacionados con un estado mutable. (https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState) in viewmodels.


# References
1. https://github.com/realm/realm-java
1. https://github.com/orbit-mvi/orbit-mvi
1. https://old.arrow-kt.io/docs/core/
1. https://developer.android.com/jetpack/compose/state
2. https://medium.com/@VolodymyrSch/android-simple-mvi-implementation-with-jetpack-compose-5ee5d6fc4908