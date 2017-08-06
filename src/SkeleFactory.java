import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.SlickException;

public class SkeleFactory 
{
	int wave;
	
	Random r;
	ArrayList<SkeleSword> swords;
	
	public SkeleFactory() throws SlickException
	{
		init();
	}
	
	public void init() throws SlickException
	{
		swords = new ArrayList<SkeleSword>();
		r = new Random();
		wave = 1;
	}
	
	public void spawn(float p1x, float p1y, float p2x, float p2y, float width, float height) throws SlickException
	{
		for (int i = 0; i < (2*wave + 5); i++)
		{
			if (r.nextInt(4) == 1){
				swords.add(new SkeleSword(r.nextInt(1224), r.nextInt(100), p1x, p1y, p2x, p2y, width, height));
			} else if (r.nextInt(4) == 2) {
				swords.add(new SkeleSword(r.nextInt(1224), (600 - r.nextInt(100)), p1x, p1y, p2x, p2y, width, height));
			} else if (r.nextInt(4) == 3) {
				swords.add(new SkeleSword(r.nextInt(200), r.nextInt(650), p1x, p1y, p2x, p2y, width, height));
			} else if (r.nextInt(4) == 4) {
				swords.add(new SkeleSword((1184 - r.nextInt(150)), (r.nextInt(650)), p1x, p1y, p2x, p2y, width, height));
			}
				
		}
	}
	
	public void clear(){
		swords.clear();
	}
	
	public ArrayList<SkeleSword> getSkeles(){
		return swords;
	}
	
	public void newLvl(){
		wave++;
	}
}
