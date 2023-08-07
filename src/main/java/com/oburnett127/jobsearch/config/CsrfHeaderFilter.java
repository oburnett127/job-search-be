package com.oburnett127.jobsearch.config;

// import org.springframework.core.Ordered;
// import org.springframework.security.web.csrf.CsrfToken;
// import jakarta.servlet.Filter;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.ServletRequest;
// import jakarta.servlet.ServletResponse;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;

// public class CsrfHeaderFilter implements Filter, Ordered {

//     @Override
//     public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//         CsrfToken csrf = (CsrfToken) req.getAttribute(CsrfToken.class.getName());
//         if (csrf != null) {
//             HttpServletResponse response = (HttpServletResponse) res;
//             response.setHeader("X-CSRF-TOKEN", csrf.getToken());
//         }
//         chain.doFilter(req, res);
//     }

//     @Override
//     public int getOrder() {
//         return Ordered.LOWEST_PRECEDENCE - 1;
//     }
// }
