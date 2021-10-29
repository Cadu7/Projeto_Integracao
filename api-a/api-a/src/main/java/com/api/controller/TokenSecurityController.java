package com.api.controller;

import java.security.Principal;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

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
	@RolesAllowed({ "Echoer", "subscriber" })
	@Produces(MediaType.TEXT_PLAIN)
	public String helloRoles(@Context SecurityContext ctx) {
		Principal caller = ctx.getUserPrincipal();
		String name = caller == null ? "anonimo" : caller.getName();
		return String.format("hello %s, isSecure: %s, authSchema: %s", name, ctx.isSecure(),
				ctx.getAuthenticationScheme());

	}

}
