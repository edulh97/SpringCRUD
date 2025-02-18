// package com.example.demo;

// import com.example.demo.controller.ProductosController;
// import com.example.demo.model.Producto;
// import com.example.demo.repository.ProductosRepository;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import java.math.BigDecimal;
// import java.util.List;
// import java.util.Optional;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @ExtendWith(MockitoExtension.class)
// @SpringBootTest
// @AutoConfigureMockMvc
// public class ProductosControllerTest {

//     private static final String BASE_URL = "/kibo/productos";

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private ProductosRepository productosRepository;

//     @InjectMocks
//     private ProductosController productosController;

//     private ObjectMapper objectMapper;

//     @BeforeEach
//     public void setUp() {
//         objectMapper = new ObjectMapper();
//     }

//     private Producto createProducto(Long id, String nombre, String descripcion, double precio, String alergenos) {
//         Producto producto = new Producto();
//         producto.setId(id);
//         producto.setNombreProducto(nombre);
//         producto.setDescripcion(descripcion);
//         producto.setPrecio(BigDecimal.valueOf(precio));
//         producto.setAlergenos(alergenos);
//         return producto;
//     }

//     @Test
//     public void testObtenerTodosLosProductos() throws Exception {
//         Producto producto = createProducto(1L, "Producto 1", "Descripción del Producto 1", 100.0, "Gluten");

//         when(productosRepository.findAll()).thenReturn(List.of(producto));

//         mockMvc.perform(get(BASE_URL))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(jsonPath("$[0].id").value(1L))
//                 .andExpect(jsonPath("$[0].nombreProducto").value("Producto 1"))
//                 .andExpect(jsonPath("$[0].descripcion").value("Descripción del Producto 1"))
//                 .andExpect(jsonPath("$[0].precio").value(100.0))
//                 .andExpect(jsonPath("$[0].alergenos").value("Gluten"));
//     }

//     @Test
//     public void testObtenerProductoPorId() throws Exception {
//         Producto producto = createProducto(1L, "Producto 1", "Descripción", 100.0, "Gluten");

//         when(productosRepository.findById(1L)).thenReturn(Optional.of(producto));

//         mockMvc.perform(get(BASE_URL + "/{id}", 1L))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(jsonPath("$.id").value(1L))
//                 .andExpect(jsonPath("$.nombreProducto").value("Producto 1"))
//                 .andExpect(jsonPath("$.descripcion").value("Descripción"))
//                 .andExpect(jsonPath("$.precio").value(100.0))
//                 .andExpect(jsonPath("$.alergenos").value("Gluten"));
//     }

//     @Test
//     public void testCrearProducto() throws Exception {
//         Producto producto = createProducto(null, "Nuevo Producto", "Descripción del Nuevo Producto", 150.0, "Sin Gluten");
//         Producto productoGuardado = createProducto(1L, "Nuevo Producto", "Descripción del Nuevo Producto", 150.0, "Sin Gluten");

//         when(productosRepository.save(any(Producto.class))).thenReturn(productoGuardado);

//         mockMvc.perform(post(BASE_URL)
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(producto)))
//                 .andExpect(status().isCreated())
//                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(jsonPath("$.id").value(1L))
//                 .andExpect(jsonPath("$.nombreProducto").value("Nuevo Producto"))
//                 .andExpect(jsonPath("$.descripcion").value("Descripción del Nuevo Producto"))
//                 .andExpect(jsonPath("$.precio").value(150.0))
//                 .andExpect(jsonPath("$.alergenos").value("Sin Gluten"));
//     }

//     @Test
//     public void testActualizarProducto() throws Exception {
//         Producto productoExistente = createProducto(1L, "Producto 1", "Descripción", 100.0, "Gluten");
//         Producto productoActualizado = createProducto(1L, "Producto Actualizado", "Descripción Actualizada", 120.0, "Sin Gluten");

//         when(productosRepository.findById(1L)).thenReturn(Optional.of(productoExistente));
//         when(productosRepository.save(any(Producto.class))).thenReturn(productoActualizado);

//         mockMvc.perform(put(BASE_URL + "/{id}", 1L)
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(productoActualizado)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.id").value(1L))
//                 .andExpect(jsonPath("$.nombreProducto").value("Producto Actualizado"))
//                 .andExpect(jsonPath("$.descripcion").value("Descripción Actualizada"))
//                 .andExpect(jsonPath("$.precio").value(120.0))
//                 .andExpect(jsonPath("$.alergenos").value("Sin Gluten"));
//     }

//     @Test
//     public void testEliminarProducto() throws Exception {
//         Producto producto = createProducto(1L, "Producto 1", "Descripción", 100.0, "Gluten");

//         when(productosRepository.findById(1L)).thenReturn(Optional.of(producto));
//         doNothing().when(productosRepository).deleteById(1L);

//         mockMvc.perform(delete(BASE_URL + "/{id}", 1L))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void testProductoNoEncontrado() throws Exception {
//         when(productosRepository.findById(1L)).thenReturn(Optional.empty());

//         mockMvc.perform(get(BASE_URL + "/{id}", 1L))
//                 .andExpect(status().isNotFound())
//                 .andExpect(jsonPath("$.message").value("Producto/Plato no encontrado"));
//     }
// }