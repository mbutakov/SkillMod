package skillsmodmbutakov.skills;

import skillsmodmbutakov.main;
import skillsmodmbutakov.network.PacketActionSkillInvinsible;
import skillsmodmbutakov.network.PacketActionSkillSpeed;

public class skillManager {
	
	private int coolDown;
	public boolean isUseKeySkill = false;
	public static boolean isCooldown;
	private int cooldown;

	
	
	public void OnUseKey(int packetId) {
		if(isUseKeySkill == true && isCooldown != true) {
			if(packetId == 1) {
			main.packetPipeline.sendToServer(new PacketActionSkillSpeed());
			} else if(packetId == 2) {
				main.packetPipeline.sendToServer(new PacketActionSkillInvinsible());
			}	
		  }
			if(isCooldown == true) {
				
				}
		}
	public void isPressedKey(boolean pressed,int Armor) {
		this.isUseKeySkill = pressed;	
		OnUseKey(Armor);
	}
  
	public int setAndGetCooldown(int cooldown) {
		this.cooldown = cooldown;
		return this.cooldown;				
	}
  
	public int getCooldown() {
		return this.cooldown;
	}

}