// Configuración de API
const API_URL = 'http://localhost:8080/api';

// Estado de la aplicación
let usuarioActual = null;
let cursosDisponibles = [];

// Inicialización
document.addEventListener('DOMContentLoaded', () => {
    cargarSesion();
    cargarCursos();
});

// Gestión de sesión
function cargarSesion() {
    const sesion = localStorage.getItem('sesionAcademiX');
    if (sesion) {
        usuarioActual = JSON.parse(sesion);
        actualizarUI();
    }
}

function guardarSesion(usuario) {
    localStorage.setItem('sesionAcademiX', JSON.stringify(usuario));
    usuarioActual = usuario;
    actualizarUI();
}

function cerrarSesion() {
    localStorage.removeItem('sesionAcademiX');
    usuarioActual = null;
    actualizarUI();
    mostrarInicio();
}

function actualizarUI() {
    const linkLogin = document.getElementById('linkLogin');
    const linkLogout = document.getElementById('linkLogout');
    const linkInscripciones = document.getElementById('linkInscripciones');
    
    if (usuarioActual) {
        linkLogin.style.display = 'none';
        linkLogout.style.display = 'inline';
        linkInscripciones.style.display = 'inline';
    } else {
        linkLogin.style.display = 'inline';
        linkLogout.style.display = 'none';
        linkInscripciones.style.display = 'none';
    }
}

// Navegación
function mostrarPagina(nombrePagina) {
    document.querySelectorAll('.pagina').forEach(p => p.classList.remove('active'));
    document.getElementById('pagina' + nombrePagina).classList.add('active');
}

function mostrarInicio() {
    mostrarPagina('Inicio');
}

function mostrarLogin() {
    mostrarPagina('Login');
}

function mostrarRegistro() {
    mostrarPagina('Registro');
}

function mostrarCatalogo() {
    mostrarPagina('Catalogo');
    cargarCursos();
}

function mostrarMisInscripciones() {
    if (!usuarioActual) {
        alert('Debes iniciar sesión');
        mostrarLogin();
        return;
    }
    mostrarPagina('Inscripciones');
    cargarInscripciones();
}

// Autenticación
async function login(event) {
    event.preventDefault();
    
    const email = document.getElementById('loginEmail').value;
    const password = document.getElementById('loginPassword').value;
    
    try {
        const response = await fetch(`${API_URL}/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password })
        });
        
        const data = await response.json();
        
        if (response.ok) {
            guardarSesion(data.usuario);
            alert('Login exitoso');
            mostrarInicio();
        } else {
            alert(data.error || 'Error en login');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error al conectar con el servidor');
    }
}

async function registrar(event) {
    event.preventDefault();
    
    const usuario = {
        nombre: document.getElementById('regNombre').value,
        apellido: document.getElementById('regApellido').value,
        email: document.getElementById('regEmail').value,
        password: document.getElementById('regPassword').value,
        codigoEstudiante: document.getElementById('regCodigo').value,
        carrera: document.getElementById('regCarrera').value,
        semestre: parseInt(document.getElementById('regSemestre').value),
        rol: 'ESTUDIANTE'
    };
    
    try {
        const response = await fetch(`${API_URL}/auth/registro`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(usuario)
        });
        
        const data = await response.json();
        
        if (response.ok) {
            alert('Registro exitoso');
            mostrarLogin();
        } else {
            alert(data.error || 'Error en registro');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error al conectar con el servidor');
    }
}

// Cursos
async function cargarCursos() {
    try {
        const response = await fetch(`${API_URL}/cursos`);
        cursosDisponibles = await response.json();
        mostrarCursos();
    } catch (error) {
        console.error('Error:', error);
        document.getElementById('listaCursos').innerHTML = '<p>Error al cargar cursos</p>';
    }
}

function mostrarCursos() {
    const container = document.getElementById('listaCursos');
    
    if (cursosDisponibles.length === 0) {
        container.innerHTML = '<p>No hay cursos disponibles</p>';
        return;
    }
    
    container.innerHTML = cursosDisponibles.map(curso => `
        <div class="curso-card">
            <h3>${curso.nombre}</h3>
            <p><strong>Código:</strong> ${curso.codigo}</p>
            <p>${curso.descripcion || 'Sin descripción'}</p>
            <div class="curso-info">
                <span><strong>Créditos:</strong> ${curso.creditos}</span>
                <span><strong>Precio:</strong> $${curso.precio}</span>
            </div>
            <div class="curso-info">
                <span><strong>Cupos:</strong> ${curso.cupoDisponible}/${curso.cupoMaximo}</span>
                <button class="btn btn-success" onclick="inscribirEnCurso('${curso.id}')" 
                    ${!usuarioActual || curso.cupoDisponible === 0 ? 'disabled' : ''}>
                    ${curso.cupoDisponible === 0 ? 'Sin cupos' : 'Inscribirse'}
                </button>
            </div>
        </div>
    `).join('');
}

async function inscribirEnCurso(cursoId) {
    if (!usuarioActual) {
        alert('Debes iniciar sesión');
        mostrarLogin();
        return;
    }
    
    const inscripcion = {
        usuarioId: usuarioActual.id,
        cursoId: cursoId,
        periodoAcademicoId: '1' // Por defecto
    };
    
    try {
        const response = await fetch(`${API_URL}/inscripciones`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(inscripcion)
        });
        
        const data = await response.json();
        
        if (response.ok) {
            alert('Inscripción exitosa');
            cargarCursos();
        } else {
            alert(data.error || 'Error en inscripción');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error al conectar con el servidor');
    }
}

// Inscripciones
async function cargarInscripciones() {
    if (!usuarioActual) return;
    
    try {
        const response = await fetch(`${API_URL}/inscripciones/usuario/${usuarioActual.id}`);
        const inscripciones = await response.json();
        
        // Cargar detalles de cursos
        for (let inscripcion of inscripciones) {
            const cursoResponse = await fetch(`${API_URL}/cursos/${inscripcion.cursoId}`);
            inscripcion.curso = await cursoResponse.json();
        }
        
        mostrarInscripciones(inscripciones);
    } catch (error) {
        console.error('Error:', error);
        document.getElementById('listaInscripciones').innerHTML = '<p>Error al cargar inscripciones</p>';
    }
}

function mostrarInscripciones(inscripciones) {
    const container = document.getElementById('listaInscripciones');
    
    if (inscripciones.length === 0) {
        container.innerHTML = '<p>No tienes inscripciones</p>';
        return;
    }
    
    container.innerHTML = inscripciones.map(insc => `
        <div class="inscripcion-card">
            <div class="inscripcion-info">
                <h4>${insc.curso.nombre}</h4>
                <p><strong>Número:</strong> ${insc.numeroInscripcion}</p>
                <p><strong>Fecha:</strong> ${new Date(insc.fechaInscripcion).toLocaleDateString()}</p>
            </div>
            <div>
                <span class="estado estado-${insc.estado.toLowerCase()}">${insc.estado}</span>
                ${insc.estado === 'CONFIRMADA' ? 
                    `<button class="btn btn-danger" onclick="cancelarInscripcion('${insc.id}')">Cancelar</button>` 
                    : ''}
            </div>
        </div>
    `).join('');
}

async function cancelarInscripcion(inscripcionId) {
    if (!confirm('¿Estás seguro de cancelar esta inscripción?')) return;
    
    try {
        const response = await fetch(`${API_URL}/inscripciones/${inscripcionId}`, {
            method: 'DELETE'
        });
        
        if (response.ok) {
            alert('Inscripción cancelada');
            cargarInscripciones();
            cargarCursos();
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error al cancelar inscripción');
    }
}
