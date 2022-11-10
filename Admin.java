import java.awt.*;
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
        frame = new JFrame();
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
			}
		});
        addUserButton.setBounds(50, 50, 50, 50);
        frame.getContentPane().add(addUserButton);

        txtGroupID = new JTextField();
        txtGroupID.setText("Group ID");
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

        JButton showMessageTotalButton = new JButton("Show Messages Total");
        showMessageTotalButton.setBounds(300, 300, 50, 50);
        frame.getContentPane().add(showMessageTotalButton);

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
        DefaultMutableTreeNode group1 = new DefaultMutableTreeNode(new UserGroup(inputNewGroupID));
        DefaultMutableTreeNode tempNode = ((DefaultMutableTreeNode) tree.getLastSelectedPathComponent());
        updateTree(group1, tempNode);
    }

    public void updateTree(DefaultMutableTreeNode nodeToAdd, DefaultMutableTreeNode containingNode) {
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		model.insertNodeInto(nodeToAdd, containingNode, containingNode.getChildCount());
		tree.scrollPathToVisible(new TreePath(nodeToAdd.getPath()));
	}
}
