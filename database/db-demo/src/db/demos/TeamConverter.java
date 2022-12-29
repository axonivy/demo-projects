package db.demos;

import javax.el.ELContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import ch.ivyteam.ivy.environment.Ivy;
import db.demos.GameInterface.GameInterfaceData;

@FacesConverter(value = "teamConverter")
@ManagedBean(name = "tc")
@SessionScoped
public class TeamConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String teamId) throws ConverterException {
		Ivy.log().info("juhuuu");
		if (teamId == null) {
			return null;
		}
		if (teamId.isBlank()) {
			return teamId;
		}
		return findFromElContext(fc, teamId);
	}

	private static Team findFromElContext(FacesContext fc, String teamId) {
		ELContext elContext = fc.getELContext();
		var gameData = (GameInterfaceData) elContext.getELResolver().getValue(elContext, null, "data");
		int id = Integer.parseInt(teamId);
		Team team = gameData.getTeams().stream()
				.filter(teams -> teams.getTID().equals(id))
				.findFirst().get();
		Ivy.log().info("the data is " + team);
		return team;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) throws ConverterException {
		if (obj instanceof Team team) { // auto null-check
           Integer tid = team.getTID();
           if (tid != null) {
        	   String id = tid.toString();
        	   Ivy.log().info("the id "+id+" was given for "+team);
			return id;
           }
		}
		if (obj instanceof String str) {
			return str;
		}
		if (obj instanceof Integer inte) {
			return inte.toString();
		}
		Ivy.log().info("id "+null+" was given for '"+obj+"' "+obj.getClass());
		return null;
	}
}