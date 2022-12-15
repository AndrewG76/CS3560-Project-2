import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

public class Admin {
    protected static Admin instance;

    private JFrame frame;
    private JTextField txtUserID;
    private JTextField txtGroupID;
    private JTree tree;
    private UserGroup root;
    private DefaultMutableTreeNode rootNode;

    private Admin(){
        root = new UserGroup("root");
        initialize();
    }

    //Singleton implementation of Admin
    public static Admin getInstance(){
        if(instance == null){
            synchronized (Admin.class){ //Synchronized allows for the usage of multi-threading processes for live, up-to-date announcements like with the Tweets
                if(instance == null){
                    instance = new Admin();
                }
            }
        }
        return instance;
    }

    public void runWindow(){
        instance.frame.setVisible(true);
    }

    //Initialize the the UI elements
    private void initialize(){
        frame = new JFrame("Admin Control Panel");
        frame.setBounds(50, 50, 805, 456);
        frame.getContentPane().setLayout(null);

        rootNode = new DefaultMutableTreeNode(root);

        txtUserID = new JTextField(); //This is where the admin inputs the name for a user to eventually be created
        txtUserID.setBounds(370, 10, 200, 50);
        frame.getContentPane().add(txtUserID);
        txtUserID.setColumns(10);

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
                addUser(txtUserID.getText());
			}
		});
        addUserButton.setBounds(580, 10, 200, 50);
        frame.getContentPane().add(addUserButton);

        txtGroupID = new JTextField(); //This is where the admin inputs the text for the new group to be created
        txtGroupID.setBounds(370, 70, 200, 50);
        frame.getContentPane().add(txtGroupID);
        txtGroupID.setColumns(10);

        JButton addGroupButton = new JButton("Add Group");
        addGroupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                addGroup(txtGroupID.getText());
            }
        });
        addGroupButton.setBounds(580, 70, 200, 50);
        frame.getContentPane().add(addGroupButton);
        
        JButton openUserViewButton = new JButton("Open User View");
        openUserViewButton.setBounds(370, 130, 410, 50);
        frame.getContentPane().add(openUserViewButton);
        openUserViewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                if(getSelected().getUserObject() instanceof User){
                    ((User) getSelected().getUserObject()).openUserView();
                }
                else{ 
                    alert("Please select a user to see user view.");
                }
            }
        });

        JButton idVerificationButton = new JButton("Verify IDs"); //we create the id verification button
        idVerificationButton.setBounds(370, 240, 200, 50);
        frame.getContentPane().add(idVerificationButton);
        idVerificationButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                UserIDCheckVisitor ucv = new UserIDCheckVisitor();
                root.accept(ucv);
                alert(ucv.getBadIDs());
            }
        });

        JButton lastUpdatedUserButton = new JButton("Last Updated User"); //and we create the last updated user button
        lastUpdatedUserButton.setBounds(580, 240, 200, 50);
        frame.getContentPane().add(lastUpdatedUserButton);
        lastUpdatedUserButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                LastUpdatedUserVisitor luv = new LastUpdatedUserVisitor();
                root.accept(luv);
                alert("The last updated user: " + luv.getLastUpdatedUser());
            }
        });

        JButton showUserCountButton = new JButton("Show User Total");
        showUserCountButton.setBounds(370, 300, 200, 50);
        frame.getContentPane().add(showUserCountButton);
        showUserCountButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                UserVisitor userVisitor = new UserVisitor();
                root.accept(userVisitor);
                alert("There are " + userVisitor.getUserCount() + " users.");
            }
        });

        JButton showGroupCountButton = new JButton("Show Group Total");
        showGroupCountButton.setBounds(580, 300, 200, 50);
        frame.getContentPane().add(showGroupCountButton);
        showGroupCountButton.addActionListener((new ActionListener(){
            public void actionPerformed(ActionEvent event){
                GroupVisitor groupVisitor = new GroupVisitor();
                root.accept(groupVisitor);
                alert("There are " + groupVisitor.getGroupCount() + " groups.");
            }
        }));

        JButton showMessageTotalButton = new JButton("Show Messages Total");
        showMessageTotalButton.setBounds(370, 360, 200, 50);
        frame.getContentPane().add(showMessageTotalButton);
        showMessageTotalButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent event){
                TotalMessageVisitor totalMessageVisitor = new TotalMessageVisitor();
                root.accept(totalMessageVisitor);
                alert("There are " + totalMessageVisitor.getMessageCount() + " messages in total.");
            }
        }));

        JButton showPositiveButton = new JButton("Show Positive Percentage");
        showPositiveButton.setBounds(580, 360, 200, 50);
        frame.getContentPane().add(showPositiveButton);
        showPositiveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                PositiveMessageVisitor positiveMessageVisitor = new PositiveMessageVisitor();
                root.accept(positiveMessageVisitor);
                alert("Percent of positive messages is: " + positiveMessageVisitor.getPositiveRatio() + " percent");
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 350, 402);
        frame.getContentPane().add(scrollPane);
        tree = new JTree(rootNode);
        scrollPane.setViewportView(tree);
    }

    private void addUser(String inputNewUserID){
        if(inputNewUserID.equals("")){
            alert("Please enter an ID!");
        }
        else{
            DefaultMutableTreeNode tempNode = getSelected();

            if(tempNode != null && tempNode.getUserObject() instanceof UserGroup){
                UserGroup tempGroup = (UserGroup) tempNode.getUserObject();

                User newUser = new User(inputNewUserID, tempGroup);
                DefaultMutableTreeNode newUserNode = new DefaultMutableTreeNode(newUser);

                if(tempGroup.addToGroup(newUser)){
                    updateTree(newUserNode, tempNode);
                }
                else{
                    alert("ID already exists. Please use a unique ID and try again.");
                }
            }
            else{
                alert("Error! Select a group and try again.");
            }
        }
    }

    private void addGroup(String inputNewGroupID){
        if(inputNewGroupID.equals("")){
            alert("Please enter an ID.");
        }
        else{
            DefaultMutableTreeNode tempNode = getSelected();
            if(tempNode != null && tempNode.getUserObject() instanceof UserGroup){
                UserGroup tempGroup = (UserGroup) tempNode.getUserObject();

                UserGroup newGroup = new UserGroup(inputNewGroupID, tempGroup);
                DefaultMutableTreeNode newGroupNode = new DefaultMutableTreeNode(newGroup);

                if(tempGroup.addToGroup(newGroup)){
                    updateTree(newGroupNode, tempNode);
                }
                else{
                    alert("ID already exists. Please try a unique ID.");
                }
            }
            else{
                alert("Error! Select a group and try again!");
            }
        }
    }

    public void updateTree(DefaultMutableTreeNode nodeToAdd, DefaultMutableTreeNode containingNode) {
		if(containingNode.getUserObject() instanceof UserGroup){
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            model.insertNodeInto((nodeToAdd), containingNode, containingNode.getChildCount());
            tree.scrollPathToVisible(new TreePath(nodeToAdd.getPath()));
        }
        else{
            alert("Cannot add to User. Select a group and try again");
        }
	}

    private void alert(String inputMessage){ //Helper method for quickly sending out the corresponding errors
        JOptionPane.showMessageDialog(null, inputMessage);
    }

    public DefaultMutableTreeNode getSelected(){
        try{
            return ((DefaultMutableTreeNode) tree.getLastSelectedPathComponent());
        }
        catch (NullPointerException e){
            return null;
        }
    }
}
