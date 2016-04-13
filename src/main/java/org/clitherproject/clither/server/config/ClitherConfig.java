package org.clitherproject.clither.server.config;

public class ClitherConfig {

    public Server server = new Server();
    public World world = new World();
    public Player player = new Player();

    public static class Server {

        public int port = 443;
        public int maxConnections = 100;
        public String ip = "localhost";
        public String name = "Unknown Server";
        
    }

    public static class World {

        public View view = new View();
        public Border border = new Border();
        public Food food = new Food();
        // public Mass mass = new Mass();

        public static class View {

            public double baseX = 1024;
            public double baseY = 592;
        }

        public static class Border {

            public double left = 0;
            public double right = 6000;
            public double top = 0;
            public double bottom = 6000;
        }

        public static class Food {

            public int spawnInterval = 20; // In ticks
            public int spawnPerInterval = 10; // How many food to spawn per interval
            public int startAmount = 100; // The amount of food to start the world with
            public int maxAmount = 500; // The maximum amount of food in the world at once
            public int foodSize = 1; // The size of food spawned on the map
            
        }
        
        // public static class Mass {

            // public int ejectedMassSize = 28; // The amount the real mass will be after ejection
            
        // }
        
    }

    public static class Player {

        public int startMass = 35;
        public int maxMass = 255000;
        // public double massDecayRate = 0.0001D; // Mass lost per tick
        // public int minMassToDecay = 9;
        public int maxNickLength = 15;
    }
}
