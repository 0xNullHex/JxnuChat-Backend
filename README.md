
***JxnuChat*** is a messaging platform just like WeChat, WhatsApp or Telegram but made by Jiangxi Normal University Student as part of java class projects. And it took around 6 months of active development and careful design.

![](https://atomrace.com/blog/wp-content/uploads/2018/05/spring-boot-logo.png)

### 🛠️ App components 
---
Just like all traditional **REST** apps structures, ***JxnuChat*** consists of the following parts
- 💻 Front end → App's UI and direct interactive part with the user, Written in React Framework with JavaScript.
- ⚙️ Back end → App's Behind the scene and where all magic happens, Crafted in Java's web Framework Spring.
- 💾 Database → all data accessed and stored on MySQL database (can be changed).


### 🎯 Features
---
For now the application is still under development, but some main objectives are archived as following.
- [x] Supports Direct Messaging using Websockets.
- [x] Secure Login using JWT (JSON Web Token) tokens.
- [x] Users can add friends by username search and send message.
- [ ] Sending Images.
- [x] User's profile.
- [ ] Cross platform support (maybe in future ? 🤫 ) 

### 📝 Get started
---
1. Setting-Up Database Connectivity with Spring Back-end.
    * Clone the project to your local machine (make sure you have git installed).
    ```sh
        git clone https://github.com/0xNullHex/JxnuChat-Backend.git    
    ```
    * Open cloned project in a code editor or ide and follow the path written below.
    ```src/main/resources/application.properties```
    * Edit the "application.properties" file to your following preferences (Make sure you already setup a MySQL/MariaDB server).
    ```
    spring.datasource.url=jdbc:mariadb://{Your_DB_IP}:{DB_Port}/{DB_Name}?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC 
    spring.datasource.username={DB_Username}
    spring.datasource.password={DB_Password}
    
    #MariaDb driver can work properly with MySQL server so no worries
    spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
    
    #For the first time start leave the option on create and after change it to update
    spring.jpa.hibernate.ddl-auto=create
    ```
2. Adding Custom Secure Key for JWT feature.
    * Access the class in the following path
    ``` src/main/java/com/myapp/chatbackend/Jwt/JwTokenVerifier.java ```
    * Add your Secure Key in the key variable (Make sure it's a strong and long Key).
    ```Java
        try {
                String token = authorizationHeader.replace("Bearer ","");
    
    //          Enter Secure Key Here
                String key = "";

                Jws<Claims> claimsJws = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                        .build()
                        .parseClaimsJws(token);
    
                Claims body = claimsJws.getBody();
                String username = body.getSubject();
                Authentication authentication = new UsernamePasswordAuthenticationToken(username,null,null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
    ```
    * Access the class in the following path
    ``` src/main/java/com/myapp/chatbackend/Jwt/JwtUsernamePasswordAuthenticationFilter.java```
    * Add Same Key here
    ```Java
        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    
    //      Enter Same Key here
            String key = " ";
            String token= Jwts.builder()
                    .setSubject(authResult.getName())
                            .setIssuedAt(new Date())
                                    .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                                            .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                                                    .compact();
            response.addHeader("Authorization","Bearer "+token);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"accessToken\":"+"\""+token+"\",");
            response.getWriter().write("\"username\":"+"\""+authResult.getName()+"\"}");
        }
    ```
    
