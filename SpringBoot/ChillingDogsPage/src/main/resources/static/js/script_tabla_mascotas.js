function buscar(){
    let busqueda = document.getElementById("buscarInput").value
    if(busqueda === ""){
        alert("Debe ingresar el nombre de la mascota en la barra de búsqueda")
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
        if(mascota.nombre.toUpperCase() === busqueda.toUpperCase()){
            let tr = document.createElement("tr")
            let celdaEstado = document.createElement("td")
            let inputEstado = document.createElement("input")
            inputEstado.type = "checkbox"
            inputEstado.disabled = true
            inputEstado.className = "form-check-input"
            if(mascota.estado === "Activo"){
                inputEstado.checked = true
            }
            celdaEstado.appendChild(inputEstado)

            let celdaImagen = document.createElement("td")
            let imagenMascota = document.createElement("img")
            imagenMascota.src = mascota.foto
            imagenMascota.alt = "Foto de " + mascota.nombre
            imagenMascota.height = 66
            imagenMascota.width = 88
            celdaImagen.appendChild(imagenMascota)

            let nombreCelda = document.createElement("td")
            nombreCelda.textContent = mascota.nombre
            let razaCelda = document.createElement("td")
            razaCelda.textContent = mascota.raza
            let duenhoCelda = document.createElement("td")
            duenhoCelda.textContent = mascota.cliente.nombre

            let detallesCelda = document.createElement("td")
            let linkDetalles = document.createElement("a")
            let imagenDetalles = document.createElement("img")
            linkDetalles.href = "/mascotas/detalles-completos/" + mascota.id
            imagenDetalles.src = "/sources/detalles-tabla.png"
            imagenDetalles.alt = "Detalles"
            imagenDetalles.height = 24
            linkDetalles.appendChild(imagenDetalles)
            detallesCelda.appendChild(linkDetalles)

            let modificarCelda = document.createElement("td")
            let linkModificar = document.createElement("a")
            let imagenModificar = document.createElement("img")
            linkModificar.href = "/mascotas/modificar/" + mascota.id
            imagenModificar.src = "/sources/editar-mascota.png"
            imagenModificar.alt = "Modificar"
            imagenModificar.height = 24
            linkModificar.appendChild(imagenModificar)
            modificarCelda.appendChild(linkModificar)

            let eliminarCelda = document.createElement("td")
            let linkEliminar = document.createElement("a")
            let imagenEliminar = document.createElement("img")
            linkEliminar.href = "/mascotas/eliminar/" + mascota.id
            imagenEliminar.src = "/sources/eliminar-mascota.png"
            imagenEliminar.alt = "Eliminar"
            imagenEliminar.height = 24
            linkEliminar.appendChild(imagenEliminar)
            eliminarCelda.appendChild(linkEliminar)

            tr.appendChild(celdaEstado)
            tr.appendChild(celdaImagen)
            tr.appendChild(nombreCelda)
            tr.appendChild(razaCelda)
            tr.appendChild(duenhoCelda)
            tr.appendChild(detallesCelda)
            tr.appendChild(modificarCelda)
            tr.appendChild(eliminarCelda)
            cuerpoTabla.appendChild(tr)
        }
    }
}