function guardarMascotaActual(mascota1){
    alert(mascota1)
    localStorage.setItem('mascota', mascota)
    window.location.href = "http://localhost:8080/mi-mascota"
}