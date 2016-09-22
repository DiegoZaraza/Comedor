package comedor.bd;


import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

public class BackupBD extends Thread {
	protected final String PATH_POSTGRESQL = "%ProgramFiles%\\PostgreSQL\\9.3\\bin\\";
	@Override
	public void run() {
		try {
			Process p = Runtime.getRuntime().exec("cmd.exe");
			String pl = "cd " + PATH_POSTGRESQL + "\n";
			p.getOutputStream().write(pl.getBytes());
			p.getOutputStream().flush();
			String d = new File(".")
			.getAbsolutePath().substring(0,
					new File(".").getAbsolutePath().length() - 2).toString()+""+File.separator+"System-Comedor"+File.separator+"Backups"+File.separator+"Backup_"+new Date(new java.util.Date().getTime())+".backup";
					
			String x = "start pg_dump.exe -i -h localhost -p 5432 -U postgres -F c -b -v -f \""+ d + "\" comedorbd\n\n";
//			System.out.println(x);
			p.getOutputStream().write(x.getBytes());
			p.getOutputStream().flush();
			
			InputStreamReader output = new InputStreamReader(p.getInputStream());
			while (output.ready() || true) {
				char[] array = new char[255];
				int num = output.read(array);
				if (num != -1) {
					String s = new String(array, 0, num);
					System.out.println(s);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
