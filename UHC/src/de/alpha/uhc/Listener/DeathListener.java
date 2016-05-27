package de.alpha.uhc.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathListener implements Listener {
	
	public DeathListener() {
	;
	}

    private  boolean pig;
    private  boolean zombie;
    private  boolean cow;
    private  boolean chicken;
    private  boolean spider;
    private  boolean skeleton;
    private  boolean sheep;
    private  boolean rabbit;

    private  int pigA;
    private  int zombieA;
    private  int cowA;
    private  int chickenA;
    private  int spiderA;
    private  int skeletonA;
    private  int sheepA;
    private  int rabbitA;

    private  Material pigM;
    private  Material zombieM;
    private  Material cowM;
    private  Material chickenM;
    private  Material spiderM;
    private  Material skeletonM;
    private  Material sheepM;
    private  Material rabbitM;


    public  void setPig(boolean pig) {
        DeathListener.pig = pig;
    }


    public  void setZombie(boolean zombie) {
        DeathListener.zombie = zombie;
    }


    public  void setCow(boolean cow) {
        DeathListener.cow = cow;
    }


    public  void setChicken(boolean chicken) {
        DeathListener.chicken = chicken;
    }


    public  void setSpider(boolean spider) {
        DeathListener.spider = spider;
    }


    public  void setSkeleton(boolean skeleton) {
        DeathListener.skeleton = skeleton;
    }


    public  void setSheep(boolean sheep) {
        DeathListener.sheep = sheep;
    }


    public  void setRabbit(boolean rabbit) {
        DeathListener.rabbit = rabbit;
    }


    public  void setPigA(int pigA) {
        DeathListener.pigA = pigA;
    }


    public  void setZombieA(int zombieA) {
        DeathListener.zombieA = zombieA;
    }


    public  void setCowA(int cowA) {
        DeathListener.cowA = cowA;
    }


    public  void setChickenA(int chickenA) {
        DeathListener.chickenA = chickenA;
    }


    public  void setSpiderA(int spiderA) {
        DeathListener.spiderA = spiderA;
    }


    public  void setSkeletonA(int skeletonA) {
        DeathListener.skeletonA = skeletonA;
    }


    public  void setSheepA(int sheepA) {
        DeathListener.sheepA = sheepA;
    }


    public  void setRabbitA(int rabbitA) {
        DeathListener.rabbitA = rabbitA;
    }


    public  void setPigM(Material pigM) {
        DeathListener.pigM = pigM;
    }


    public  void setZombieM(Material zombieM) {
        DeathListener.zombieM = zombieM;
    }


    public  void setCowM(Material cowM) {
        DeathListener.cowM = cowM;
    }


    public  void setChickenM(Material chickenM) {
        DeathListener.chickenM = chickenM;
    }


    public  void setSpiderM(Material spiderM) {
        DeathListener.spiderM = spiderM;
    }


    public  void setSkeletonM(Material skeletonM) {
        DeathListener.skeletonM = skeletonM;
    }


    public  void setSheepM(Material sheepM) {
        DeathListener.sheepM = sheepM;
    }


    public  void setRabbitM(Material rabbitM) {
        DeathListener.rabbitM = rabbitM;
    }


    @EventHandler
    public void onDie(EntityDeathEvent e) {

        Entity p = e.getEntity();

        if (p instanceof Player) {
            return;
        }

        if (p instanceof Pig) {
            if (!(pig)) return;
            e.getDrops().clear();
            p.getWorld().dropItem(p.getLocation(), new ItemStack(pigM, pigA));
        }

        if (p instanceof Zombie) {
            if (!(zombie)) return;
            e.getDrops().clear();
            p.getWorld().dropItem(p.getLocation(), new ItemStack(zombieM, zombieA));
        }

        if (p instanceof Cow) {
            if (!(cow)) return;
            e.getDrops().clear();
            p.getWorld().dropItem(p.getLocation(), new ItemStack(cowM, cowA));
        }

        if (p instanceof Chicken) {
            if (!(chicken)) return;
            e.getDrops().clear();
            p.getWorld().dropItem(p.getLocation(), new ItemStack(chickenM, chickenA));
        }

        if (p instanceof Spider) {
            if (!(spider)) return;
            e.getDrops().clear();
            p.getWorld().dropItem(p.getLocation(), new ItemStack(spiderM, spiderA));
        }

        if (p instanceof Skeleton) {
            if (!(skeleton)) return;
            e.getDrops().clear();
            p.getWorld().dropItem(p.getLocation(), new ItemStack(skeletonM, skeletonA));
        }

        if (p instanceof Sheep) {
            if (!(sheep)) return;
            e.getDrops().clear();
            p.getWorld().dropItem(p.getLocation(), new ItemStack(sheepM, sheepA));
        }

        if (p instanceof Rabbit) {
            if (!(rabbit)) return;
            e.getDrops().clear();
            p.getWorld().dropItem(p.getLocation(), new ItemStack(rabbitM, rabbitA));
        }
    }
}
