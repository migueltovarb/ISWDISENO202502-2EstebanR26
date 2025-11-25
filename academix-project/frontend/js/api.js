const API_URL = 'http://localhost:8080/api';

const api = {
    baseURL: API_URL,
    
    // Configuración de headers
    getHeaders() {
        const token = localStorage.getItem('token');
        return {
            'Content-Type': 'application/json',
            ...(token && { 'Authorization': `Bearer ${token}` })
        };
    },

    // Usuarios
    async login(email, password) {
        const response = await fetch(`${API_URL}/usuarios/login`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify({ email, password })
        });
        return response.json();
    },

    async registrar(usuario) {
        const response = await fetch(`${API_URL}/usuarios/registro`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(usuario)
        });
        return response.json();
    },

    // Cursos
    async getCursos() {
        const response = await fetch(`${API_URL}/cursos`, {
            headers: this.getHeaders()
        });
        return response.json();
    },

    async getCurso(id) {
        const response = await fetch(`${API_URL}/cursos/${id}`, {
            headers: this.getHeaders()
        });
        return response.json();
    },

    async crearCurso(curso) {
        const response = await fetch(`${API_URL}/cursos`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(curso)
        });
        return response.json();
    },

    // Inscripciones
    async inscribirse(inscripcion) {
        const response = await fetch(`${API_URL}/inscripciones`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(inscripcion)
        });
        return response.json();
    },

    async getMisInscripciones(estudianteId) {
        const response = await fetch(`${API_URL}/inscripciones/estudiante/${estudianteId}`, {
            headers: this.getHeaders()
        });
        return response.json();
    },

    async cancelarInscripcion(inscripcionId) {
        const response = await fetch(`${API_URL}/inscripciones/${inscripcionId}`, {
            method: 'DELETE',
            headers: this.getHeaders()
        });
        return response.json();
    },

    // Profesores
    async getProfesores() {
        const response = await fetch(`${API_URL}/profesores`, {
            headers: this.getHeaders()
        });
        return response.json();
    },

    async crearProfesor(profesor) {
        const response = await fetch(`${API_URL}/profesores`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(profesor)
        });
        return response.json();
    },

    // Horarios
    async getHorariosCurso(cursoId) {
        const response = await fetch(`${API_URL}/horarios/curso/${cursoId}`, {
            headers: this.getHeaders()
        });
        return response.json();
    },

    async crearHorario(horario) {
        const response = await fetch(`${API_URL}/horarios`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(horario)
        });
        return response.json();
    },

    // Calificaciones
    async getCalificacionesEstudiante(estudianteId) {
        const response = await fetch(`${API_URL}/calificaciones/estudiante/${estudianteId}`, {
            headers: this.getHeaders()
        });
        return response.json();
    },

    async crearCalificacion(calificacion) {
        const response = await fetch(`${API_URL}/calificaciones`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(calificacion)
        });
        return response.json();
    },

    async actualizarCalificacion(id, calificacion) {
        const response = await fetch(`${API_URL}/calificaciones/${id}`, {
            method: 'PUT',
            headers: this.getHeaders(),
            body: JSON.stringify(calificacion)
        });
        return response.json();
    },

    // Pagos
    async getPagosEstudiante(estudianteId) {
        const response = await fetch(`${API_URL}/pagos/estudiante/${estudianteId}`, {
            headers: this.getHeaders()
        });
        return response.json();
    },

    async crearPago(pago) {
        const response = await fetch(`${API_URL}/pagos`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(pago)
        });
        return response.json();
    },

    async confirmarPago(pagoId, datos) {
        const response = await fetch(`${API_URL}/pagos/${pagoId}/confirmar`, {
            method: 'PUT',
            headers: this.getHeaders(),
            body: JSON.stringify(datos)
        });
        
        if (!response.ok) {
            throw new Error('Error al confirmar el pago');
        }
        
        return response.json();
    },

    // Notificaciones
    async getNotificaciones(usuarioId) {
        const response = await fetch(`${API_URL}/notificaciones/usuario/${usuarioId}`, {
            headers: this.getHeaders()
        });
        return response.json();
    },

    async marcarNotificacionLeida(id) {
        const response = await fetch(`${API_URL}/notificaciones/${id}/leer`, {
            method: 'PUT',
            headers: this.getHeaders()
        });
        return response.json();
    },

    // Admin Dashboard
    async getDashboard() {
        const response = await fetch(`${API_URL}/admin/dashboard`, {
            headers: this.getHeaders()
        });
        return response.json();
    },

    // Método fetch genérico para otras operaciones
    async fetch(endpoint, options = {}) {
        const url = endpoint.startsWith('http') ? endpoint : `${API_URL}${endpoint}`;
        
        const defaultOptions = {
            headers: this.getHeaders()
        };
        
        const mergedOptions = {
            ...defaultOptions,
            ...options,
            headers: {
                ...defaultOptions.headers,
                ...(options.headers || {})
            }
        };
        
        const response = await fetch(url, mergedOptions);
        
        if (!response.ok) {
            throw new Error(`Error en la petición: ${response.status}`);
        }
        
        return response.json();
    }
};