package server.presentation;

public class Program {

	public static void main(String[] args) {
			
		try {
			MainView mainView = new MainView();
			MainViewController mainViewController=new MainViewController(mainView);
			mainView.addController(mainViewController);
			mainView.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
