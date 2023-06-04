package com.gameduca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gameduca.dto.JwtDTO;
import com.gameduca.dto.LoginUsuario;
import com.gameduca.dto.NuevoUsuario;
import com.gameduca.entity.Alumno;
import com.gameduca.entity.Profesor;
import com.gameduca.entity.Rol;
import com.gameduca.entity.RolNombre;
import com.gameduca.entity.Usuario;
import com.gameduca.security.JwtProvider;
import com.gameduca.service.AlumnoService;
import com.gameduca.service.ProfesorService;
import com.gameduca.service.RolService;
import com.gameduca.service.UsuarioService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    AlumnoService alumnoService;
    
    @Autowired
    ProfesorService profesorService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity("campos vacíos o email inválido", HttpStatus.BAD_REQUEST);
        if(usuarioService.existePorNombre(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity("ese nombre ya existe", HttpStatus.BAD_REQUEST);
        if(usuarioService.existePorEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity("ese email ya existe", HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        String rol = nuevoUsuario.getRol();
        Set<Rol> roles = new HashSet<>();
       
            switch (rol) {
                case "ROLE_ADMIN":
                    Rol rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();
                    roles.add(rolAdmin);
                    Profesor profesor = new Profesor();
                    profesor.setUsuario(usuario);
                    profesorService.crearProfesor(profesor);
                    break;
                default:
                    Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
                    roles.add(rolUser);
                    Alumno alumno = new Alumno();
                    alumno.setUsuario(usuario);
                    alumnoService.crearAlumno(alumno);
            }
        
        usuario.setRoles(roles);
        usuarioService.guardar(usuario);
        //return new ResponseEntity("usuario guardado", HttpStatus.CREATED);
        
        
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(nuevoUsuario.getNombreUsuario(), nuevoUsuario.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, usuario, userDetails.getAuthorities());

        
        
        return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors())
            return new ResponseEntity("campos vacíos o email inválido", HttpStatus.BAD_REQUEST);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Obtener el objeto de usuario
            Optional<Usuario> optionalUser = usuarioService.getByNombreUsuario(userDetails.getUsername());
            if (optionalUser.isPresent()) {
                Usuario user = optionalUser.get();
                JwtDTO jwtDTO = new JwtDTO(jwt, user, userDetails.getAuthorities());
                return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity("Usuario no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
        	throw new Exception(e);
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
//        if(bindingResult.hasErrors())
//            return new ResponseEntity("campos vacíos o email inválido", HttpStatus.BAD_REQUEST);
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtProvider.generateToken(authentication);
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
//        return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
//    }
}
