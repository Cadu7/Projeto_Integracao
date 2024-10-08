package com.api.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;

import java.security.Principal;

@Path("/security")
@ApplicationScoped
public class TokenSecurityController {

    @GET
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@Context SecurityContext ctx) {
        Principal caller = ctx.getUserPrincipal();
        String name = caller == null ? "anonimo" : caller.getName();
        return String.format("hello %s, isSecure: %s, authSchema: %s", name, ctx.isSecure(),
                ctx.getAuthenticationScheme());

    }

    @GET
    @Path("/roles")
    @RolesAllowed({"Echoer", "subscriber"})
    @Produces(MediaType.TEXT_PLAIN)
    public String helloRoles(@Context SecurityContext ctx) {
        Principal caller = ctx.getUserPrincipal();
        String name = caller == null ? "anonimo" : caller.getName();
        return String.format("hello %s, isSecure: %s, authSchema: %s", name, ctx.isSecure(),
                ctx.getAuthenticationScheme());

    }

}
