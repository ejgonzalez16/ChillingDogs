function buscar(){
    let busqueda = document.getElementById("buscarInput").value
    if(busqueda === ""){
        alert("inserte algo a buscar")
        return
    }
    actualizarTabla(busqueda)
}

function actualizarTabla(busqueda){
    let tabla = document.getElementById("tablaMascotas")
    let cuerpoTabla = tabla.getElementsByTagName("tbody")[0];
    //Eliminar todas las filas
    while (cuerpoTabla.firstChild) {
        cuerpoTabla.removeChild(cuerpoTabla.firstChild);
    }
    for(let mascota of mascotas){
        if(mascota.mascota.nombre.toUpperCase() === busqueda.toUpperCase()){
            let tr = document.createElement("tr")
            let celdaEstado = document.createElement("td")
            let inputEstado = document.createElement("input")
            inputEstado.type = "checkbox"
            inputEstado.disabled = true
            inputEstado.className = "form-check-input"
            if(mascota.mascota.estado === "Activo"){
                inputEstado.checked = true
            }
            celdaEstado.appendChild(inputEstado)
            let celdaImagen = document.createElement("td")
            let imagenMascota = document.createElement("img")
            imagenMascota.src = mascota.mascota.foto
            imagenMascota.alt = "Foto de " + mascota.mascota.nombre
            imagenMascota.height = 66
            celdaImagen.appendChild(imagenMascota)
            let nombreCelda = document.createElement("td")
            nombreCelda.textContent = mascota.mascota.nombre
            let razaCelda = document.createElement("td")
            razaCelda.textContent = mascota.mascota.raza
            let duenhoCelda = document.createElement("td")
            duenhoCelda.textContent = mascota.nombreDueno
            let detallesCelda = document.createElement("td")
            let linkDetalles = document.createElement("a")
            linkDetalles.href = "/mascotas/modificar/" + mascota.mascota.id
            let imagenDetalles = document.createElement("img")
            imagenDetalles.src = "/sources/editar-mascota.png"
            imagenDetalles.alt = "Detalles"
            imagenDetalles.height = 24
            linkDetalles.appendChild(imagenDetalles)
            detallesCelda.appendChild(linkDetalles)
            tr.appendChild(celdaEstado)
            tr.appendChild(celdaImagen)
            tr.appendChild(nombreCelda)
            tr.appendChild(razaCelda)
            tr.appendChild(duenhoCelda)
            tr.appendChild(detallesCelda)
            cuerpoTabla.appendChild(tr)
        }
    }
}