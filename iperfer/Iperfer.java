public class Iperfer {

	public static void main(String[] args) {
		int i = 0;
		String arg = "";
		boolean client = false;
		boolean clientOrServer = false;
		int time = 0;
		int port = 0;
		String host = "";
		
		while (i < args.length) {
			if (args[i].equals("-c")) {
				if (args.length != 7) {
        				System.err.println("Error: missing or additional arguments");
        				System.exit(-1);
	        		}
        			client = true;
				clientOrServer = true;
        			break;
			} 
			if (args[i].equals("-s")) {
				if (args.length != 3) {
        				System.err.println("Error: missing or additional arguments");
        				System.exit(-1);
        			}
        			client = false;
				clientOrServer = true;
        			break;
			}
			i++;
		}

		if (!clientOrServer) {
        		System.err.println("Error: missing or additional arguments");
        		System.exit(-1);
		}
		
		i = 0;
		
		while (i < args.length && args[i].startsWith("-")) {
            arg = args[i++];
            
            if (arg.charAt(1) == 'c' || arg.charAt(1) == 's')
            	continue;
            
            switch (arg.charAt(1)) {
            	case 't':
            		if (client) {
            			try {
            				time = Integer.parseInt(args[i++]);
            			} catch (NumberFormatException nfe) {
            				System.err.println("Error: time must be an integer");
                			System.exit(-1);
            			}
            		} else {
            			System.err.println("Error: missing or additional arguments");
            			System.exit(-1);
            		}
            		break;
            	case 'p':
            		try {
        				port = Integer.parseInt(args[i++]);
        			} catch (NumberFormatException nfe) {
        				System.err.println("Error: port number must be in the range 1024 to 65535");
            			System.exit(-1);
        			}
            		if (port <= 1024 || port >= 65535) {
            			System.err.println("Error: port number must be in the range 1024 to 65535");
            			System.exit(-1);
            		}
            		break;
            	case 'h':
            		if (client) {
            			host = args[i++];
            		} else {
            			System.err.println("Error: missing or additional arguments");
            			System.exit(-1);
            		}
            		break;
            	default:
            		System.err.println("Error: missing or additional arguments");
            }
        }
		
		if (client)
			System.out.println("Client: Host " + host + " Port " + port + " Time " + time);
		
		if (!client)
			System.out.println("Server: Port " + port);
	}
}
