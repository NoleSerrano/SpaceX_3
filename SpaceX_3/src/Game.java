
//Space X
//A game by Nole Serrano
//Computer Science Period 3
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Game extends javax.swing.JFrame implements KeyListener, MouseListener {
    
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double health = 100;
    private int score = 0;
    private int level;
    private final Timer t1;
    private final Timer t2;
    private final Timer t3;
    private static ArrayList<Point> Asteroid;
    private static ArrayList<Point> Stars;
    private static ArrayList<Enemy> Enemies;
    private static ArrayList<Point> speedpicks;
    private static ArrayList<Laser> PLasers;
    private static ArrayList<Explosion> Explosions;
    private static ArrayList<BossExplosion> bExplosions;
    private static ArrayList<LaserExplosion> laserEXPS;
    private static ArrayList<Point> Healths;
    private static ArrayList<Boss> Bosses;
    private static ArrayList<Rock> Rocks;
    private final Image asteroidpic1;
    private final Image spaceshippic;
    private final Image wrenchpic;
    private final Image spaceimage;
    private final Image space1;
    private final Image space2;
    private final Image space3;
    private final Image space4;
    private final Image enemypic;
    private final Image plasmapic;
    private final Image exp1;
    private final Image exp2;
    private final Image exp3;
    private final Image exp4;
    private final Image healthpic;
    private final Image bosspic;
    private final Image bosspic1;
    private final Image bosspic2;
    private final Image bosspic3;
    private final Image snoop;
    private final Image leaf;
    private final Image rock1;
    private final Image rock2;
    private final Image rock3;
    private final Image laserEXP;
    private final Image pause;
    private Random r;
    private Point spaceship;
    private Point speed1;
    private Point speed2;
    private double movementspeed = 4;
    private boolean gameover = false;
    private boolean legit = true;
    private int finalscore;
    private int startinglevel;
    private int a;
    private int vx;
    private int vy;
    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;
    private boolean paused = false;

    //uses 1280 x 1024 for the amount of pixels on school computer with r.nextInt()
    //game not yet optimized for other resolutions 
    private final int maxX = 1280;
    private final int maxY = 1024;
    
    public Game() {
        
        ImageIcon icon = new ImageIcon("spacex.jpg");
        //note: player wins @ level 540 (to be implemented)
        do {
            level = Integer.parseInt((String) JOptionPane.showInputDialog(null,
                    "What level would you like to start off on?",
                    "Created by Nole Serrano", JOptionPane.INFORMATION_MESSAGE, icon, null, ""));
            if (level < 1 || level > 419) {
                JOptionPane.showMessageDialog(null, "That is not valid input!\n"
                        + "You must enter a number between 0 and 420 exclusive");
            } else if (level % 9 == 0 || level % 27 == 0) {
                JOptionPane.showMessageDialog(null, "That is not valid input!\n"
                        + "You must enter a number that's not divisible by"
                        + " 9 or 27");
            }
        } while (level < 1 || level > 419 || level % 9 == 0 || level % 27 == 0);
        startinglevel = level;
        
        this.setUndecorated(true);
        initComponents();
        setResizable(false);
        
        getContentPane().setBackground(Color.BLACK);
        this.setBounds(0, 0, maxX, maxY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.createBufferStrategy(2);
        
        addKeyListener(this);
        addMouseListener(this);

        //images
        ClassLoader cloader = Game.class.getClassLoader();
        asteroidpic1 = Toolkit.getDefaultToolkit().getImage(cloader.getResource("asteroid1.png"));
        prepareImage(asteroidpic1, this);
        spaceshippic = Toolkit.getDefaultToolkit().getImage(cloader.getResource("spaceship2.png"));
        prepareImage(spaceshippic, this);
        wrenchpic = Toolkit.getDefaultToolkit().getImage(cloader.getResource("wrench.png"));
        prepareImage(wrenchpic, this);
        spaceimage = Toolkit.getDefaultToolkit().getImage(cloader.getResource("spaceimage1.png"));
        prepareImage(spaceimage, this);
        enemypic = Toolkit.getDefaultToolkit().getImage(cloader.getResource("enemy1.png"));
        prepareImage(enemypic, this);
        plasmapic = Toolkit.getDefaultToolkit().getImage(cloader.getResource("plasma.png"));
        prepareImage(plasmapic, this);
        exp1 = Toolkit.getDefaultToolkit().getImage(cloader.getResource("exp1.png"));
        prepareImage(exp1, this);
        exp2 = Toolkit.getDefaultToolkit().getImage(cloader.getResource("exp2.png"));
        prepareImage(exp2, this);
        exp3 = Toolkit.getDefaultToolkit().getImage(cloader.getResource("exp3.png"));
        prepareImage(exp3, this);
        exp4 = Toolkit.getDefaultToolkit().getImage(cloader.getResource("exp4.png"));
        prepareImage(exp4, this);
        healthpic = Toolkit.getDefaultToolkit().getImage(cloader.getResource("health.png"));
        prepareImage(healthpic, this);
        //boss after being damaged
        bosspic = Toolkit.getDefaultToolkit().getImage(cloader.getResource("boss.png"));
        prepareImage(bosspic, this);
        bosspic1 = Toolkit.getDefaultToolkit().getImage(cloader.getResource("boss1.png"));
        prepareImage(bosspic1, this);
        bosspic2 = Toolkit.getDefaultToolkit().getImage(cloader.getResource("boss2.png"));
        prepareImage(bosspic2, this);
        bosspic3 = Toolkit.getDefaultToolkit().getImage(cloader.getResource("boss3.png"));
        prepareImage(bosspic3, this);
        snoop = Toolkit.getDefaultToolkit().getImage(cloader.getResource("snoop1.png"));
        prepareImage(snoop, this);
        leaf = Toolkit.getDefaultToolkit().getImage(cloader.getResource("leaf.png"));
        prepareImage(leaf, this);
        space1 = Toolkit.getDefaultToolkit().getImage(cloader.getResource("space1.jpg"));
        prepareImage(space1, this);
        space2 = Toolkit.getDefaultToolkit().getImage(cloader.getResource("space2.jpg"));
        prepareImage(space2, this);
        space3 = Toolkit.getDefaultToolkit().getImage(cloader.getResource("space3.jpg"));
        prepareImage(space3, this);
        space4 = Toolkit.getDefaultToolkit().getImage(cloader.getResource("space4.jpg"));
        prepareImage(space4, this);
        rock1 = Toolkit.getDefaultToolkit().getImage(cloader.getResource("rock1.png"));
        prepareImage(rock1, this);
        rock2 = Toolkit.getDefaultToolkit().getImage(cloader.getResource("rock2.png"));
        prepareImage(rock2, this);
        rock3 = Toolkit.getDefaultToolkit().getImage(cloader.getResource("rock3.png"));
        prepareImage(rock3, this);
        laserEXP = Toolkit.getDefaultToolkit().getImage(cloader.getResource("laserEXP.png"));
        prepareImage(laserEXP, this);
        pause = Toolkit.getDefaultToolkit().getImage(cloader.getResource("pause.png"));
        prepareImage(pause, this);
        
        r = new Random();

        //initiliazes asteroids
        Asteroid = new ArrayList();
        for (int i = 1; i <= 25; i++) {
            Asteroid.add(new Point(r.nextInt(maxX), r.nextInt(maxY)));
        }

        //rocks
        Rocks = new ArrayList();
        for (int i = 0; i < 10; i++) {
            int a = (int) (Math.random() * 3);
            if (a == 0) {
                Rocks.add(new Rock(r.nextInt(maxX), r.nextInt(maxY), rock1));
            } else if (a == 1) {
                Rocks.add(new Rock(r.nextInt(maxX), r.nextInt(maxY), rock2));
            } else {
                Rocks.add(new Rock(r.nextInt(maxX), r.nextInt(maxX), rock3));
            }
        }

        //initializes stars
        Stars = new ArrayList();
        for (int i = 0; i < 100; i++) {
            Stars.add(new Point(r.nextInt(maxX), r.nextInt(maxY)));
        }

        //initializes enemies
        Enemies = new ArrayList();
        addEnemies(3);

        //initializes speed and health pickups
        speed1 = new Point(r.nextInt(maxX), r.nextInt(maxY));
        speed2 = new Point(r.nextInt(maxX), r.nextInt(maxY));
        speedpicks = new ArrayList();
        speedpicks.add(speed1);
        speedpicks.add(speed2);
        
        PLasers = new ArrayList();
        Explosions = new ArrayList();
        Healths = new ArrayList();
        Bosses = new ArrayList();
        bExplosions = new ArrayList();
        laserEXPS = new ArrayList();

        //initializes player controlled spaceship
        spaceship = new Point(640, 512);
        
        ActionListener task1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                doAction1(e1);
            }
        };
        
        ActionListener task2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                shootLasers(e1);
            }
        };
        
        ActionListener task3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                pausedGame(e1);
            }
        };
        
        t1 = new Timer(20, task1);
        t1.start();

        //if hold down rt click sprays the lasers
        t2 = new Timer(30, task2);

        //if paused timer
        t3 = new Timer(20, task3);
        
    }
    
    private void doAction1(ActionEvent e) {
        update();
        go();
        Collision();

        //updates explosions
        for (int i = Explosions.size() - 1; i >= 0; i--) {
            Explosion E = Explosions.get(i);
            E.incCount();
            if (E.getCount() > 5) {
                Explosions.remove(i);
            }
        }
        
        for (int i = laserEXPS.size() - 1; i >= 0; i--) {
            LaserExplosion LE = laserEXPS.get(i);
            LE.update();
            if (LE.getCount() > 3) {
                laserEXPS.remove(i);
            }
        }
        
        for (int i = bExplosions.size() - 1; i >= 0; i--) {
            BossExplosion E = bExplosions.get(i);
            E.incCount();
            if ((level != 420 || level != 421) && E.getCount() > 4) {
                bExplosions.remove(i);
            } else if (E.getCount() > 26) {
                bExplosions.remove(i);
            }
        }
        
        if (Enemies.isEmpty() && Bosses.isEmpty()) {
            nextLevel();
            score += 5;
        }
        
        if (!PLasers.isEmpty()) {
            for (int i = 0; i < PLasers.size(); i++) {
                Laser L = PLasers.get(i);
                //if laser has made less than 250 moves then can move
                if (L.getInc() < 250) {
                    //updates laser's position
                    L.Move();
                } else {
                    //removes laser
                    PLasers.remove(i);
                }
            }
        }
        
        if (!Bosses.isEmpty()) {
            for (Boss B : Bosses) {
                B.update(spaceship);
                //doesn't move it closer if certain distance away
                if (Math.sqrt(Math.pow((spaceship.y + 15) - (B.getY() + 50), 2)
                        + Math.pow((spaceship.x + 15) - (B.getX() + 50), 2)) > 175) {
                    B.Move(new Point(B.getX(), B.getY()), spaceship);
                }
                for (int i = B.getPlasmaList().size() - 1; i >= 0; i--) {
                    Plasma P = B.getPlasmaList().get(i);
                    if (P.getInc() < 1000) {
                        P.Move();
                    } else {
                        B.getPlasmaList().remove(i);
                    }
                }
                
            }
        }
        
        for (Enemy E : Enemies) {
            E.update(spaceship);
            if (Math.sqrt(Math.pow((spaceship.y + 15) - (E.getY() + 15), 2)
                    + Math.pow((spaceship.x + 15) - (E.getX() + 15), 2)) > 150) {
                E.Move(new Point(E.getX(), E.getY()), spaceship);
            }
            if (!E.getPlasmaList().isEmpty()) {
                for (int i = E.getPlasmaList().size() - 1; i >= 0; i--) {
                    Plasma P = E.getPlasmaList().get(i);
                    //if plasma has made less than # moves then can move
                    if (P.getInc() < 500) {
                        P.Move();
                    } else {
                        //removes the plasma bullet
                        E.getPlasmaList().remove(i);
                    }
                }
            }
        }
        paintGame();
    }
    
    private void shootLasers(ActionEvent e) {
        PLasers.add(new Laser(MouseInfo.getPointerInfo().getLocation().x,
                MouseInfo.getPointerInfo().getLocation().y, spaceship));
    }
    
    private void pausedGame(ActionEvent e) {
        paintGame();
    }
    
    private void nextLevel() {
        //increments level
        level++;
        //adds enemies
        Enemies.add(new Enemy(new Point(r.nextInt(maxX), r.nextInt(maxY)), 500));
        if (level % 3 == 0) {
            addEnemies(2);
        }
        if (level % 4 == 0) {
            addEnemies(3);
        }
        if (level % 10 == 0) {
            addEnemies(4);
        }
        if (level % 24 == 0) {
            addHealths(2);
        } else if (level % 6 == 0) {
            addHealths(1);
        }
        if ((level + 1) % 27 == 0) {
            addHealths(3);
        }
        if (level % 27 == 0) {
            Bosses.add(new Boss(new Point(r.nextInt(maxX), r.nextInt(maxY)), 350, 20));
            Bosses.add(new Boss(new Point(r.nextInt(maxX), r.nextInt(maxY)), 350, 20));
        } else if (level % 9 == 0) {
            Bosses.add(new Boss(new Point(r.nextInt(maxX), r.nextInt(maxY)), 350, 20));
        }
        if (level == 420) {
            //snoop dogg with 4.2k health
            Bosses.add(new Boss(new Point(r.nextInt(maxX), r.nextInt(maxY)), 4200, 25));
            addHealths(20);
        }
        if ((level - 3) % 6 == 0 && !(level % 27 == 0)) {
            //stops user from stockpiling healths
            Healths.clear();
        }
    }
    
    private void Collision() {
        
        Polygon p = new Polygon();
        p.addPoint(spaceship.x, spaceship.y);
        p.addPoint(spaceship.x + 30, spaceship.y);
        p.addPoint(spaceship.x + 30, spaceship.y + 30);
        p.addPoint(spaceship.x, spaceship.y + 30);

        //boss collisions
        if (!Bosses.isEmpty()) {
            for (int i = Bosses.size() - 1; i >= 0; i--) {
                Boss B = Bosses.get(i);
                
                if (!B.getPlasmaList().isEmpty()) {
                    //checks if an enemy plasma hit player
                    int k = B.getPlasmaList().size() - 1;
                    //blzit
                    while (k >= 0) {
                        Plasma P = B.getPlasmaList().get(k);
                        if (p.intersects(P.getX(), P.getY(), 18, 18)) {
                            B.getPlasmaList().remove(k);
                            health -= 3.5;
                        }
                        k--;
                    }
                }
                
                if (!PLasers.isEmpty()) {
                    //loops through the lasers and make sure that the enemy is alive
                    //so that there is no indexoutofboundsexception error
                    boolean alive = true;
                    int j = PLasers.size() - 1;
                    while (alive && j >= 0) {
                        Laser L = PLasers.get(j);
                        Line2D Laser = new Line2D.Double(L.getX1(), L.getY1(), L.getX2(), L.getY2());
                        if (Laser.intersects(B.getX(), B.getY(), 100, 100)) {
                            laserEXPS.add(new LaserExplosion(L.getX2(), L.getY2()));
                            PLasers.remove(j);
                            B.decHealth();
                            score += 2;
                            if (B.getHealth() <= 0) {
                                bExplosions.add(new BossExplosion(B));
                                Bosses.remove(i);
                                alive = false;
                            }
                        }
                        j--;
                    }
                }
            }
        }

        //if statement to prevent player from suddenly coming back to life;
        if (health > 0) {
            if (!Healths.isEmpty()) {
                for (int i = Healths.size() - 1; i >= 0; i--) {
                    Point P = Healths.get(i);
                    if (p.intersects(P.x, P.y, 25, 25)) {
                        if (health <= 200) {
                            if (health > 170) {
                                health = 200;
                            } else {
                                health += 30;
                            }
                        }
                        Healths.remove(i);
                    }
                }
            }
        }
        if (!speedpicks.isEmpty()) {
            for (int i = speedpicks.size() - 1; i >= 0; i--) {
                Point P = speedpicks.get(i);
                if (p.intersects(P.x, P.y, 30, 30)) {
                    speedpicks.remove(i);
                    movementspeed += 1.5;
                }
            }
        }
        
        if (!Enemies.isEmpty()) {
            for (int i = Enemies.size() - 1; i >= 0; i--) {
                Enemy E = Enemies.get(i);
                
                if (!E.getPlasmaList().isEmpty()) {
                    //checks if an enemy plasma hit player
                    int k = E.getPlasmaList().size() - 1;
                    while (k >= 0) {
                        Plasma P = E.getPlasmaList().get(k);
                        if (p.intersects(P.getX(), P.getY(), 18, 18)) {
                            E.getPlasmaList().remove(k);
                            health -= 3.2;
                        }
                        k--;
                    }
                }
                
                if (!PLasers.isEmpty()) {
                    //loops through the lasers and make sure that the enemy is alive
                    //so that there is no indexoutofboundsexception error
                    boolean alive = true;
                    int j = PLasers.size() - 1;
                    while (alive && j >= 0) {
                        Laser L = PLasers.get(j);
                        Line2D Laser = new Line2D.Double(L.getX1(), L.getY1(), L.getX2(), L.getY2());
                        if (Laser.intersects(E.getX(), E.getY(), 30, 30)) {
                            laserEXPS.add(new LaserExplosion(L.getX2(), L.getY2()));
                            PLasers.remove(j);
                            E.decHealth();
                            score += 2;
                            if (E.getHealth() <= 0) {
                                Explosions.add(new Explosion(E));
                                Enemies.remove(i);
                                alive = false;
                            }
                        }
                        j--;
                    }
                }
            }
        }
    }
    
    public void addEnemies(int n) {
        //adds n amount of enemies
        for (int i = 0; i < n; i++) {
            Enemies.add(new Enemy(new Point(r.nextInt(maxX), r.nextInt(maxY)), 500));
        }
    }
    
    public void addHealths(int n) {
        for (int i = 0; i < n; i++) {
            Healths.add(new Point(r.nextInt(maxX), r.nextInt(maxY)));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });
    }
    
    public void paintGame() {
        
        BufferStrategy bf = this.getBufferStrategy();
        Graphics g = null;
        
        try {
            g = bf.getDrawGraphics();
            //----------------------------------------------------------------//
            if (paused) {
                g.setColor(Color.black);
                g.fillRect(0, 0, maxX, maxY);
                g.drawImage(pause, 565, 437, 150, 150, null);
                g.setFont(new Font("Aharoni", 1, 50));
                g.setColor(Color.white);
                g.drawString("PAUSED", 543, 420);
                
            } else if (health > 0) {
                //if player is alive
                g.drawImage(spaceimage, 0, 0, maxX, maxY, null);

                //stars
                g.setColor(Color.white);
                for (Point p : Stars) {
                    g.fillOval(p.x, p.y, 2, 2);
                }

                //pickups
                if (!speedpicks.isEmpty()) {
                    for (Point p : speedpicks) {
                        g.drawImage(wrenchpic, p.x, p.y, 30, 30, null);
                    }
                }

                //draws asteroids
                for (Point p : Asteroid) {
                    g.drawImage(asteroidpic1, p.x, p.y, 30, 30, null);
                }
                for (Rock R : Rocks) {
                    g.drawImage(R.getPic(), R.getX(), R.getY(), R.getSize(),
                            R.getSize(), null);
                }

                //draws healths
                if (!Healths.isEmpty()) {
                    if (level == 420) {
                        for (Point p : Healths) {
                            g.drawImage(leaf, p.x, p.y, 25, 25, null);
                        }
                    } else {
                        for (Point p : Healths) {
                            g.drawImage(healthpic, p.x, p.y, 25, 25, null);
                        }
                    }
                    
                }

                //enemy bosses
                if (!Bosses.isEmpty()) {
                    for (Boss B : Bosses) {
                        if (level == 420) {
                            g.drawImage(snoop, B.getX(), B.getY(), 100, 100, null);
                        } else if (B.getHealth() > 100) {
                            g.drawImage(bosspic, B.getX(), B.getY(), 100, 100, null);
                        } else if (B.getHealth() > 50) {
                            g.drawImage(bosspic1, B.getX(), B.getY(), 100, 100, null);
                        } else if (B.getHealth() > 25) {
                            g.drawImage(bosspic2, B.getX(), B.getY(), 100, 100, null);
                        } else {
                            g.drawImage(bosspic3, B.getX(), B.getY(), 100, 100, null);
                        }
                        
                    }
                }

                //enemy spaceships
                for (Enemy E : Enemies) {
                    g.drawImage(enemypic, E.getX(), E.getY(), 30, 30, null);
                }
                
                for (Boss B : Bosses) {
                    for (Plasma P : B.getPlasmaList()) {
                        g.drawImage(plasmapic, P.getX(), P.getY(), 18, 18, null);
                    }
                }

                //draws plasmas of enemies
                for (Enemy E : Enemies) {
                    for (Plasma P : E.getPlasmaList()) {
                        g.drawImage(plasmapic, P.getX(), P.getY(), 18, 18, null);
                    }
                }

                //player controlled spaceship
                g.drawImage(spaceshippic, spaceship.x, spaceship.y, 30, 30, null);

                //lasers
                g.setColor(Color.red);
                if (!PLasers.isEmpty()) {
                    for (Laser L : PLasers) {
                        g.drawLine(L.getX1(), L.getY1(), L.getX2(), L.getY2());
                    }
                }

                //laser explosions
                if (!laserEXPS.isEmpty()) {
                    for (LaserExplosion L : laserEXPS) {
                        g.drawImage(laserEXP, L.getX(), L.getY(), 10, 10, null);
                    }
                }

                //explosions
                if (!Explosions.isEmpty()) {
                    for (Explosion E : Explosions) {
                        //0,1 --plan to add more explosion pics in future
                        int r = (int) (Math.random() * 2);
                        if (r == 0) {
                            g.drawImage(exp1, E.getX(), E.getY(), 40, 40, null);
                        } else if (r == 1) {
                            g.drawImage(exp2, E.getX(), E.getY(), 40, 40, null);
                        }
                    }
                }
                
                if (!bExplosions.isEmpty()) {
                    for (BossExplosion E : bExplosions) {
                        if (level == 420 || level == 421) {
                            g.drawImage(exp4, E.getX(), E.getY(), 120, 120, null);
                        } else {
                            g.drawImage(exp3, E.getX(), E.getY(), 120, 120, null);
                        }
                        
                    }
                }

                //text
                if (level != 420) {
                    g.setColor(Color.yellow);
                    Font myFont = new Font("Calibre", 1, 15);
                    g.setFont(myFont);
                    String s1 = "Score: " + score;
                    String s2 = "Health: " + (int) health;
                    String s3 = "Level: " + level;
                    g.drawString(s1, 10, 20);
                    g.drawString(s2, 10, 40);
                    g.drawString(s3, 1194, 1012);
                } else {
                    g.setColor(Color.green);
                    Font myFont = new Font("Calibre", 1, 15);
                    g.setFont(myFont);
                    String s1 = "Score: " + score;
                    String s2 = "Health: " + (int) health;
                    String s3 = "Level: " + level;
                    String s4 = "Snoop's Health";
                    
                    g.drawString(s1, 10, 60);
                    g.drawString(s2, 10, 80);
                    g.drawString(s3, 1194, 1012);
                    myFont = new Font("Verdana", 1, 25);
                    g.setFont(myFont);
                    
                    for (Boss B : Bosses) {
                        if (B.getHealth() > 0) {
                            g.setColor(Color.gray);
                            g.fillRect(465, 933, 350, 50);
                            g.setColor(Color.LIGHT_GRAY);
                            g.fillRect(468, 936, 344, 44);
                            g.setColor(Color.green);
                            g.fillRect(468, 936, 344 * (int) B.getHealth() / 4200, 44);
                            g.drawString(s4, 530, 923);
                        }
                    }
                }

                //boss health
                if (level % 9 == 0) {
                    g.setColor(Color.gray);
                    g.fillRect(515, 958, 250, 25);
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(517, 960, 246, 21);
                    g.setColor(Color.red);
                    if (Bosses.size() == 2 && level % 27 == 0) {
                        g.fillRect(517, 960, 123 * (int) Bosses.get(0).getHealth() / 350
                                + 123 * (int) Bosses.get(1).getHealth() / 350, 21);
                    } else if (Bosses.size() == 1 && level % 27 == 0) {
                        g.fillRect(517, 960, 123 * (int) Bosses.get(0).getHealth() / 350, 21);
                    } else if (Bosses.size() == 1 && level % 9 == 0) {
                        g.fillRect(517, 960, 246 * (int) Bosses.get(0).getHealth() / 350, 21);
                    }
                }

                //player's health
                g.setColor(Color.gray);
                g.fillRect(540, 993, 200, 14);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(542, 995, 196, 10);
                g.setColor(Color.yellow);
                if (health >= 200) {
                    g.setColor(new Color(226, 187, 31));
                    g.fillRect(542, 995, 196, 10);
                } else if (health >= 100) {
                    g.fillRect(542, 995, 196, 10);
                    g.setColor(new Color(226, 187, 31));
                    g.fillRect(542, 995, 196 * ((int) health - 100) / 100, 10);
                } else {
                    g.fillRect(542, 995, 196 * (int) health / 100, 10);
                }

                //if gameover
            } else {
                if (!gameover) {
                    gameover = true;
                    finalscore = score;
                    a = (int) (Math.random() * 4);
                }
                if (a == 0) {
                    g.drawImage(space1, 0, 0, maxX, maxY, null);
                    g.setColor(Color.red);
                } else if (a == 1) {
                    g.drawImage(space2, 0, 0, maxX, maxY, null);
                    g.setColor(Color.yellow);
                } else if (a == 2) {
                    g.drawImage(space3, 0, 0, maxX, maxY, null);
                    g.setColor(Color.black);
                } else {
                    g.drawImage(space4, 0, 0, maxX, maxY, null);
                    g.setColor(Color.white);
                }
                Font myFont = new Font("Impact", 1, 30);
                g.setFont(myFont);
                g.drawString("GAMEOVER", 565, 512);
                g.drawString("FINAL SCORE: " + finalscore, 545, 540);
                g.setFont(new Font("Arial Black", 1, 14));
                if (legit) {
                    if (a == 2) {
                        g.setColor(Color.white);
                    }
                    g.drawString("LEGIT (" + startinglevel + ")", 15, 1013);
                }
                
            }
            //----------------------------------------------------------------//
        } finally {
            g.dispose();
        }
// Shows the contents of the backbuffer on the screen.
        bf.show();

        //Tell the System to do the Drawing now, otherwise it can take a few extra ms until
        //Drawing is done which looks very jerky
        Toolkit.getDefaultToolkit().sync();
        
    }
    
    private void update() {
        vx = 0;
        vy = 0;
        if (down) {
            vy = (int) movementspeed;
        }
        if (up) {
            vy = -(int) movementspeed;
        }
        if (left) {
            vx = -(int) movementspeed;
        }
        if (right) {
            vx = (int) movementspeed;
        }
    }
    
    public void go() {
        if ((spaceship.x + 15 > 0 && left) || (spaceship.x + 15 < maxX && right)) {
            if (up || down) {
                spaceship.x += vx / 1.2;
            } else {
                spaceship.x += vx;
            }
        }
        if ((spaceship.y + 15 > 0 && up) || (spaceship.y + 15 < maxY && down)) {
            if (right || left) {
                spaceship.y += vy / 1.2;
            } else {               
                spaceship.y += vy;
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        
        int key = ke.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            //moves player foward
            up = true;
        } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            //moves player down
            down = true;
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            //moves player right
            right = true;
        } else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            //moves player left
            left = true;
        } else if (key == KeyEvent.VK_ADD && health > 0) {
            legit = false;
            health += 100;
        } else if (key == KeyEvent.VK_SUBTRACT && health > 0) {
            health -= 100;
        } else if (key == KeyEvent.VK_BACK_SPACE && health > 0 && !paused) {
            //kills player
            health = 0;
        } else if (key == KeyEvent.VK_BACK_SPACE && gameover) {
            //leaves game
            System.exit(0);
        } else if (paused == false && key == KeyEvent.VK_SPACE && health > 0) {
            t1.stop();
            t3.start();
            paused = true;
        } else if (paused == true && key == KeyEvent.VK_SPACE && health > 0) {
            t1.start();
            t3.stop();
            paused = false;
        }
        update();
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        int key = ke.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            up = false;
        } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            down = false;
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            right = false;
        } else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            left = false;
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        //creates a red laser that travels in direction of mouse click
        //PLasers.add(new Laser(me.getX(), me.getY(), spaceship));
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        t2.start();
    }
    
    @Override
    public void mouseReleased(MouseEvent me) {
        t2.stop();
    }
    
    @Override
    public void mouseEntered(MouseEvent me) {
        
    }
    
    @Override
    public void mouseExited(MouseEvent me) {
        
    }
//credit for smooth movement
//https://www.gamedev.net/topic/348728-java-smooth-keyboard-input/
}
