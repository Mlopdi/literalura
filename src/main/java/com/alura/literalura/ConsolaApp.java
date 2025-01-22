package com.alura.literalura;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alura.literalura.dto.AutorDto;
import com.alura.literalura.dto.LibroDto;
import com.alura.literalura.service.AutorService;
import com.alura.literalura.service.LibroService;

@Component
public class ConsolaApp implements CommandLineRunner {

    private LibroService libroService;
    private AutorService autorService;

    public ConsolaApp(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            boolean salir = false;
            Scanner scanner = new Scanner(System.in);
            
            while (!salir) {
                System.out.println("""
                ***********************************************************
                Bienvenido a LiteraAlura!

                Por favor seleccione una opción para continuar:
                
                1) Buscar libro por título
                2) Listar libros registrados
                3) Listar autores registrados
                4) Listar autores vivos en un determinado año
                5) Listar libros por idioma
                6) Salir
                
                **********************************************************
                """);

                Integer opcion = scanner.nextInt();

                if (opcion > 0  && opcion <= 6) {
                    switch (opcion) {
                        case 1:
                            System.out.println("\nIngresa el nombre del libro a buscar!\n\n");
                            scanner.nextLine();
                            String nombreLibro = scanner.nextLine();
                            Set<LibroDto> librosEncontrados = libroService.obtenerLibroPorNombre(nombreLibro);
                            if (!librosEncontrados.isEmpty()) {
                                librosEncontrados.forEach(x -> System.out.println(x.toString()));
                            } else {
                                System.out.println("\nNo se han encontrado libros con ese nombre!\n\n");
                            }
                            break;
                        case 2:
                            System.out.println("\nListando todos los libros disponibles!\n\n");
                            Set<LibroDto> libros = libroService.obtenerLibros();
                            if (!libros.isEmpty()) {
                                libros.forEach(x -> System.out.println(x.toString()));
                            } else {
                                System.out.println("\nNo hay libros para mostrar!\n\n");
                            }
                            break;
                        case 3:
                            System.out.println("\nListando todos los autores disponibles!\n\n");
                            Set<AutorDto> autores = autorService.obtenerAutores();
                            if (!autores.isEmpty()) {
                                autores.forEach(x -> System.out.println(x.toString()));
                            } else {
                                System.out.println("\nNo hay autores para mostrar!\n\n");
                            }
                            break;
                        case 4:
                            System.out.println("\nPor favor ingrese el año por el que quiere consultar.\n\n");
                            Integer anho = scanner.nextInt();
                            Set<AutorDto> autoresVivos = autorService.obtenerSiEstabaVivo(anho);
                            if (!autoresVivos.isEmpty()) {
                                autoresVivos.forEach(x -> System.out.println(x.toString()));
                            } else {
                                System.out.println("\nNo hay autores vivos de ese año para mostrar!\n\n");
                            }
                            break;
                        case 5:
                            System.out.println("\nIngrese el las 2 siglas del lenguaje por el cual quiere listar!\n\n");
                            scanner.nextLine();
                            String siglasIdioma = scanner.nextLine();
                            Set<LibroDto> librosEncontradosPorIdioma = libroService.obtenerLibrosPorIdioma(siglasIdioma);
                            if (!librosEncontradosPorIdioma.isEmpty()) {
                                librosEncontradosPorIdioma.forEach(x -> System.out.println(x.toString()));
                            } else {
                                System.out.println("\nNo se han encontrado libros con ese idioma!\n\n");
                            }
                            break;
                        case 6:
                            scanner.close();
                            salir = true;
                            System.out.println("\nSaliendo del programa, gracias por utilizar nuestros servicios!.\n\n");
                            break;
                        default:
                            break;
                    }
                } else {
                    System.out.println("\nOpción inválida, por favor seleccione correctamente una de la lista.\n\n");
                }
            }
        } catch (InputMismatchException errorNoNumero) {
            System.out.println("\nOpción inválida, por favor seleccione correctamente una de la lista.\n\n");
            run("");
        }
        
    }
    
}
