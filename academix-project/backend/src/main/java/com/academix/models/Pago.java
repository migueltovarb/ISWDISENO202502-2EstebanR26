package com.academix.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pagos")
public class Pago {
    
    @Id
    private String id;
    
    private String inscripcionId;
    private String estudianteId;
    private String transaccionId;  // ✅ Solo uno para ID de transacción
    private BigDecimal monto;
    private String metodoPago;
    private String estadoPago;  // ✅ PENDIENTE, COMPLETADO, FALLIDO
    
    @CreatedDate
    private LocalDateTime fechaCreacion;  // ✅ Se establece automáticamente al crear
    private LocalDateTime fechaPago;      // ✅ Se establece manualmente al confirmar
    
    private String stripePaymentId;  // Opcional para integraciones futuras
}