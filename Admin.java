import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

public class Admin {
    private static Admin instance = null;

    private JFrame frame;
    private JTextField txtUserID;
    private JTextField txtGroupID;
    private JPanel rootDisplay;
    private JTree tree;
    private UserGroup root;
    private DefaultMutableTreeNode rootNode;

    private Admin(){
        root = new UserGroup("root");
        initialize();
    }

    public static Admin getInstance(){
        if(instance == null){
            synchronized(Admin.class){ //Synchronized allows for the usage of multi-threading processes for live, up-to-date announcements like with the Tweets
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

    private void initialize(){
        frame = new JFrame("Admin Control Panel");
        frame.setBounds(500, 500, 500, 500);
        frame.getContentPane().setLayout(null);

        rootNode = new DefaultMutableTreeNode(root);

        txtUserID = new JTextField();
        txtUserID.setText("User ID");
        txtUserID.setBounds(100, 100, 50, 50);
        frame.getContentPane().add(txtUserID);
        txtUserID.setColumns(10);

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                addUser(txtUserID.getText());
			}
		});
        addUserButton.setBounds(50, 50, 50, 50);
        frame.getContentPane().add(addUserButton);

        txtGroupID = new JTextField();
        txtGroupID.setBounds(150, 150, 50, 50);
        frame.getContentPane().add(txtGroupID);
        txtGroupID.setColumns(10);

        JButton addGroupButton = new JButton("Add Group");
        addGroupButton.setBounds(200, 200, 50, 50);
        frame.getContentPane().add(addGroupButton);
        addGroupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                addGroup(txtGroupID.getText());
            }
        });
        
        JButton openUserViewButton = new JButton("Open User View");
        openUserviewButton.setBounds(250, 250, 50, 50);
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

        JButton showMessageTotalButton = new JButton("Show Messages Total");
        showMessageTotalButton.setBounds(300, 300, 50, 50);
        frame.getContentPane().add(showMessageTotalButton);
        showMessageTotalButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent event){
                TotalMessageVisitor totalMessageVisitor = new TotalMessageVisitor();
                root.accept(totalMessageVisitor);
                alert("There are " + totalMessageVisitor.getMessageCount() + " messages in total.");
            }
        }));

        JButton showPositiveButton = new JButton("Show Positive Percentage");
        showPositiveButton.setBounds(350, 350, 50, 50);
        frame.getContentPane().add(showPositiveButton);

        JButton showUserCountButton = new JButton("Show User Total");
        showUserCountButton.setBounds(400, 400, 50, 50);
        frame.getContentPane().add(showUserCountButton);

        JButton showGroupCountButton = new JButton("Show Group Total");
        showGroupCountButton.setBounds(450, 450, 50, 50);
        frame.getContentPane().add(showGroupCountButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(450, 450, 50, 100);
        frame.getContentPane().add(scrollPane);
        tree = new JTree(rootNode);
        scrollPane.setViewportView(tree);
    }

    private void addGroup(String inputNewGroupID){
        if(inputNewGroupID.equals("")){
            alert("Please enter an ID.");
        }
        else{
            DefaultMutableTreeNode tempNode = getSelected();
            UserGroup tempGroup = (UserGroup) tempNode.getUserObject();

            UserGroup newGroup = new UserGroup(inputNewGroupID, tempGroup);
            DefaultMutableTreeNode newGroupNode = new DefaultMutableTreeNode(inputNewGroupID);

            if(tempGroup.addToGroup(newGroup)){
                updateTree(newGroupNode, tempNode);
            }
            else{
                alert("ID already exists. Please try a unique ID.");
            }
        }
    }

    private void addUser(String inputNewUserID){
        if(inputNewUserID.equals("")){
            alert("Please enter an ID!");
        }
        else{
            DefaultMutableTreeNode tempNode = getSelected();
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
    }

    public void updateTree(DefaultMutableTreeNode nodeToAdd, DefaultMutableTreeNode containingNode) {
		if(containingNode.getUserObject() instanceof Group){
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            model.insertNodeInto((nodeToAdd), containingNode, containingNode.getChildCount());
            tree.scrollPathToVisible(new TreePath(nodeToAdd.getPath()));
        }
        else{
            alert("Cannot add to User. Select a group and try again");
        }
	}

    private void alert(String inputMessage){
        JOptionPane.showMessageDialog(null, inputMessage);
    }

    public DefaultMutableTreeNode getSelected(){
        return ((DefaultMutableTreeNode) tree.getLastSelectedPathComponent())
    }
}
