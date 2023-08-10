// package com.oburnett127.jobsearch.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.web.csrf.CsrfToken;
// import org.springframework.security.web.csrf.CsrfTokenRepository;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// import jakarta.servlet.http.Cookie;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// /***************************************
//  * Not using cookie for csrf token for now because of the new spring security standards.
//  * This has to do with BREACH protection. Decided to use a header for csrf token instead.
//  * *************************************/

//  @RestController
//  public class CsrfController {
 
//     //  @Autowired
//     //  private CsrfTokenRepository csrfTokenRepository;
 
//     //  @GetMapping("/csrf-token")
//     //  public ResponseEntity<String> getCsrfToken(HttpServletRequest request) {
//     //      CsrfToken csrfToken = csrfTokenRepository.generateToken(request);
//     //      String tokenValue = csrfToken.getToken();
//     //      // Log or print the token value
//     //      System.out.println("Generated CSRF token: " + tokenValue);
//     //      // Return the token in a custom header or response body as needed
//     //      return ResponseEntity.ok().header("X-CSRF-TOKEN", tokenValue).build();
//     //  }

//     @GetMapping("/csrf-token")
//     public void getCsrfToken(HttpServletResponse response, HttpServletRequest request) {
//         CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    
//         if (csrfToken != null) {
//             Cookie cookie = new Cookie("XSRF-TOKEN", csrfToken.getToken());
//             cookie.setPath("/");
//             response.addCookie(cookie);

//             //response.setHeader("X-CSRF-TOKEN", csrfToken.getToken());
//         }
//     }
// }