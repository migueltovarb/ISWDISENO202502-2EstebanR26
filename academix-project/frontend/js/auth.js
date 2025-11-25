const auth = {
    // Guardar usuario en localStorage
    setUser(usuario) {
        localStorage.setItem('usuario', JSON.stringify(usuario));
        localStorage.setItem('usuarioId', usuario.id);
        localStorage.setItem('rol', usuario.rol);
    },

    // Obtener usuario actual
    getUser() {
        const usuario = localStorage.getItem('usuario');
        return usuario ? JSON.parse(usuario) : null;
    },

    // Obtener ID del usuario
    getUserId() {
        return localStorage.getItem('usuarioId');
    },

    // Obtener rol del usuario
    getRol() {
        return localStorage.getItem('rol');
    },

    // Verificar si está autenticado
    isAuthenticated() {
        return !!this.getUser();
    },

    // Cerrar sesión
    logout() {
        // Limpiar todo el localStorage
        localStorage.clear();
        
        // Detectar si estamos en /pages/ o en la raíz
        const enPagesFolder = window.location.pathname.includes('/pages/');
        const loginUrl = enPagesFolder ? 'login.html' : 'pages/login.html';
        
        // Redirigir al login
        window.location.href = loginUrl;
    },

    // Redirigir según el rol
    redirectToDashboard() {
        const rol = this.getRol();
        
        // Detectar si estamos en /pages/ o en la raíz
        const enPagesFolder = window.location.pathname.includes('/pages/');
        const prefijo = enPagesFolder ? '' : 'pages/';
        
        switch(rol) {
            case 'ADMIN':
                window.location.href = prefijo + 'admin-dashboard.html';
                break;
            case 'PROFESOR':
                window.location.href = prefijo + 'profesor-dashboard.html';
                break;
            case 'ESTUDIANTE':
                window.location.href = prefijo + 'estudiante-dashboard.html';
                break;
            default:
                window.location.href = prefijo + 'login.html';
        }
    },

    // Verificar acceso por rol
    requireRole(...roles) {
        if (!this.isAuthenticated()) {
            const enPagesFolder = window.location.pathname.includes('/pages/');
            window.location.href = enPagesFolder ? 'login.html' : 'pages/login.html';
            return false;
        }
        
        const userRole = this.getRol();
        if (!roles.includes(userRole)) {
            alert('No tienes permisos para acceder a esta página');
            this.redirectToDashboard();
            return false;
        }
        
        return true;
    }
};

// Verificar autenticación al cargar cualquier página protegida
function requireAuth() {
    if (!auth.isAuthenticated()) {
        const enPagesFolder = window.location.pathname.includes('/pages/');
        window.location.href = enPagesFolder ? 'login.html' : 'pages/login.html';
        return false;
    }
    return true;
}