package ACommand;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ACommand {
	void execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
