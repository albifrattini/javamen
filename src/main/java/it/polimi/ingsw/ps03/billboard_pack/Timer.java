package it.polimi.ingsw.ps03.billboard_pack;

public class Timer extends Thread{

	private boolean isFinished;
	private int timer;
	private boolean isRunning;
	private boolean stopped;
	
	public Timer(int timer){
		this.timer = timer;
	}
	
	public void startTimer(){
		isRunning = true;
		isFinished = false;
		stopped = false;
		this.start();
	}
	
	public void run(){
		for(int i = timer; i >= 0; i--){
			try{
				if(isStopped()) break;
				Thread.sleep(1000);
			}catch(InterruptedException e){
				i = 0;
				isRunning = false;
				isFinished = true;
			}		
			if(i == 0){
				System.out.println("Timer Scaduto!");
			}
			else{
				System.out.println("Timer in esecuzione: - " +i);
			}
		}
		isRunning = false;
		isFinished = true;
		stopped = false;
	}

	public boolean itsOver(){
		return isFinished;
	}
	
	public void stopTimer(){
		this.stopped = true;
	}
	
	public boolean isStopped(){
		return stopped;
	}
	
	public boolean stillRunning(){
		return isRunning;
	}
	
}
