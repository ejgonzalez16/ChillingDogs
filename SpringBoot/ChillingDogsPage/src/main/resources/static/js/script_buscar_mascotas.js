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
            imagenMascota.height = "66px"
            celdaImagen.appendChild(imagenMascota)
            let nombreCelda = document.createElement("td")
            nombreCelda.textContent = mascota.mascota.nombre
            let razaCelda = document.createElement("td")
            razaCelda.textContent = mascota.mascota.raza
            let duenhoCelda = document.createElement("td")
            duenhoCelda.textContent = mascota.nombreDueno
            let detallesCelda = document.createElement("td")
            let svgDetalles = document.createElement("svg")
            svgDetalles.xmlns = "http://www.w3.org/2000/svg"
            svgDetalles.width = "32"
            svgDetalles.height = "32"
            svgDetalles.viewBox = "0 0 256 256"
            let pathDetalles = document.createElement("path")
            pathDetalles.d = "M32,64a8,8,0,0,1,8-8H216a8,8,0,0,1,0,16H40A8,8,0,0,1,32,64Zm8,72h72a8,8,0,0,0,0-16H40a8,8,0,0,0,0,16Zm88,48H40a8,8,0,0,0,0,16h88a8,8,0,0,0,0-16Zm109.66,13.66a8,8,0,0,1-11.32,0L206,177.36A40,40,0,1,1,217.36,166l20.3,20.3A8,8,0,0,1,237.66,197.66ZM184,168a24,24,0,1,0-24-24A24,24,0,0,0,184,168Z"
            svgDetalles.appendChild(pathDetalles)
            detallesCelda.appendChild(svgDetalles)
            tr.appendChild(celdaEstado)
            tr.appendChild(celdaImagen)
            tr.appendChild(nombreCelda)
            tr.appendChild(razaCelda)
            tr.appendChild(duenhoCelda)
            tr.appendChild(detallesCelda)
        }
    }
}