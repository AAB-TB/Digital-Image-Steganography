/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import ClassFiles.ImageFileView;
import ClassFiles.ImagePreview;

import ClassFiles.PasswordDialog;
import ClassFiles.Process;
import ClassFiles.Properties;
import ClassFiles.SPanel;
import ClassFiles.Utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

/**
 *
 * @author Jhamela
 */
public class MainGui extends JFrame implements ActionListener {

    JMenuBar mb;
    JScrollPane sp1, sp2;
    ImageIcon ico;
    FileDialog fd1, fd2;
    SPanel p1, p2;
    JTabbedPane t;
    JMenu file, edit, view, tools, help;
    JMenuItem f1, f2, f3, f4, f5, f6, f7, f8, fopen;
    JMenuItem e1, e2, e3, e4, e5, e6, e7;
    JMenuItem t1, t2, t3;
    String epath, path, url, fn, dn, tfn, tdn, ipath, sipath, sepath, tpath, ext;
    Process pd, pe, ps;
    FilenameFilter filefilter;

    private JPanel contentPane, panel_1, panel_2, panel_3, panel_4, panel_5, panel_8;
    public JLabel lblNewLabel_1, lblNewLabel_2, lblNewLabel_3, lblNewLabel;
    private String file_path;
    public JPanel panel_6;
    //public ImageIcon ico;
    private JPasswordField pwdPassword;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_4;

    private JLabel lblImageStatus;
    private JLabel lblData;
    public JPanel panel_7;
    public TextArea textArea;

    public boolean isImage, isData, isSteganoImage;

//	private FileDialog fd1;
//	public String dn,fn,path,url,tdn,tfn,tpath,epath,ipath;
    public JButton btnExit, btnHelp, btnClearText, btnEncode, btnDecode;
    public JButton button_img_open, button_img_delete, button_data_open, button_data_delete, button_stegano_open, button_stegano_delete;

    //JCheckBox chckbxNewCheckBox_1;

    String s;

    public MainGui() {

        setTitle("Steganography");
//        setResizable(false);
        
        addMenuBar();

        //body starts
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 940, 800);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        panel_2 = new JPanel();
        panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, null, null));
        panel_2.setBounds(20, 668, 894, 45);
        contentPane.add(panel_2);
        panel_2.setLayout(null);

        

        btnHelp = new JButton("Exit");
        btnHelp.setBounds(795, 11, 89, 23);
        panel_2.add(btnHelp);
        btnHelp.addActionListener(this);

        panel_3 = new JPanel();
        panel_3.setBounds(20, 11, 525, 646);
        panel_3.setOpaque(true);
        panel_3.setBackground(Color.WHITE);
        panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, null, null));
        contentPane.add(panel_3);
        panel_3.setLayout(null);

        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("E:\\fahim\\steganography.jpg"));
        lblNewLabel.setBounds(31, 25, 457, 86);
        panel_3.add(lblNewLabel);

//		pwdPassword = new JPasswordField();
//		pwdPassword.setBounds(107, 470, 194, 20);
//		panel_3.add(pwdPassword);
//		JCheckBox chckbxNewCheckBox = new JCheckBox("Password:");
//		chckbxNewCheckBox.setBounds(6, 469, 95, 23);
//		panel_3.add(chckbxNewCheckBox);
        btnClearText = new JButton("Clear All\r\n");
        btnClearText.setBounds(185, 470, 117, 23);
        panel_3.add(btnClearText);
        btnClearText.addActionListener(this);

        btnEncode = new JButton("Encode");
        btnEncode.setBounds(10, 512, 203, 30);
        panel_3.add(btnEncode);
        btnEncode.addActionListener(this);

        btnDecode = new JButton("Decode");
        btnDecode.setBounds(294, 512, 194, 30);
        panel_3.add(btnDecode);
        btnDecode.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setBounds(10, 136, 478, 30);
        panel_3.add(panel);
        SpringLayout sl_panel = new SpringLayout();
        panel.setLayout(sl_panel);

        JLabel lblImageFile = new JLabel("Image File:");
        sl_panel.putConstraint(SpringLayout.WEST, lblImageFile, 0, SpringLayout.WEST, panel);
        sl_panel.putConstraint(SpringLayout.SOUTH, lblImageFile, -6, SpringLayout.SOUTH, panel);
        sl_panel.putConstraint(SpringLayout.EAST, lblImageFile, 63, SpringLayout.WEST, panel);
        panel.add(lblImageFile);

        textField = new JTextField("", 15);
        sl_panel.putConstraint(SpringLayout.NORTH, textField, 4, SpringLayout.NORTH, panel);
        sl_panel.putConstraint(SpringLayout.WEST, textField, 8, SpringLayout.EAST, lblImageFile);
        sl_panel.putConstraint(SpringLayout.SOUTH, textField, -6, SpringLayout.SOUTH, panel);
        panel.add(textField);

        button_img_open = new JButton("Open");

        button_img_open.addActionListener(this);
        sl_panel.putConstraint(SpringLayout.EAST, textField, -21, SpringLayout.WEST, button_img_open);
        button_img_open.setIcon(null);
        sl_panel.putConstraint(SpringLayout.WEST, button_img_open, 279, SpringLayout.WEST, panel);
        sl_panel.putConstraint(SpringLayout.SOUTH, button_img_open, -3, SpringLayout.SOUTH, panel);
        panel.add(button_img_open);

        button_img_delete = new JButton("Delete");
        button_img_delete.addActionListener(this);
        sl_panel.putConstraint(SpringLayout.EAST, button_img_open, -15, SpringLayout.WEST, button_img_delete);
        sl_panel.putConstraint(SpringLayout.SOUTH, button_img_delete, -3, SpringLayout.SOUTH, panel);
        sl_panel.putConstraint(SpringLayout.WEST, button_img_delete, -86, SpringLayout.EAST, panel);
        sl_panel.putConstraint(SpringLayout.EAST, button_img_delete, 0, SpringLayout.EAST, panel);
        panel.add(button_img_delete);

        panel_1 = new JPanel();
        panel_1.setBounds(10, 177, 478, 30);
        panel_3.add(panel_1);
        SpringLayout sl_panel_1 = new SpringLayout();
        panel_1.setLayout(sl_panel_1);

        textField_1 = new JTextField("", 15);
        sl_panel_1.putConstraint(SpringLayout.WEST, textField_1, 91, SpringLayout.WEST, panel_1);
        sl_panel_1.putConstraint(SpringLayout.SOUTH, textField_1, 0, SpringLayout.SOUTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.EAST, textField_1, -101, SpringLayout.EAST, panel_1);
        panel_1.add(textField_1);

        lblImageStatus = new JLabel("Image Status:");
        sl_panel_1.putConstraint(SpringLayout.SOUTH, lblImageStatus, -3, SpringLayout.SOUTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.EAST, lblImageStatus, -13, SpringLayout.WEST, textField_1);
        panel_1.add(lblImageStatus);

        panel_4 = new JPanel();
        panel_4.setBounds(10, 222, 478, 30);
        panel_3.add(panel_4);
        SpringLayout sl_panel_4 = new SpringLayout();
        panel_4.setLayout(sl_panel_4);

        lblData = new JLabel("Data File:");
        sl_panel_4.putConstraint(SpringLayout.NORTH, lblData, 0, SpringLayout.NORTH, panel_4);
        sl_panel_4.putConstraint(SpringLayout.WEST, lblData, 0, SpringLayout.WEST, panel_4);
        panel_4.add(lblData);

        textField_2 = new JTextField("", 15);
        sl_panel_4.putConstraint(SpringLayout.NORTH, textField_2, 0, SpringLayout.NORTH, panel_4);
        sl_panel_4.putConstraint(SpringLayout.WEST, textField_2, 12, SpringLayout.EAST, lblData);
        sl_panel_4.putConstraint(SpringLayout.EAST, textField_2, 202, SpringLayout.EAST, lblData);
        panel_4.add(textField_2);

        button_data_open = new JButton("Open");

        button_data_open.addActionListener(this);
        sl_panel_4.putConstraint(SpringLayout.NORTH, button_data_open, 0, SpringLayout.NORTH, panel_4);
        sl_panel_4.putConstraint(SpringLayout.WEST, button_data_open, 20, SpringLayout.EAST, textField_2);
        sl_panel_4.putConstraint(SpringLayout.EAST, button_data_open, 106, SpringLayout.EAST, textField_2);
        panel_4.add(button_data_open);

        button_data_delete = new JButton("Delete");
        button_data_delete.addActionListener(this);
        sl_panel_4.putConstraint(SpringLayout.NORTH, button_data_delete, 0, SpringLayout.NORTH, panel_4);
        sl_panel_4.putConstraint(SpringLayout.WEST, button_data_delete, -80, SpringLayout.EAST, panel_4);
        sl_panel_4.putConstraint(SpringLayout.EAST, button_data_delete, 0, SpringLayout.EAST, panel_4);
        panel_4.add(button_data_delete);

        panel_5 = new JPanel();
        panel_5.setBounds(10, 565, 478, 30);
        panel_3.add(panel_5);
        SpringLayout sl_panel_5 = new SpringLayout();
        panel_5.setLayout(sl_panel_5);

        JLabel lblStegoFile = new JLabel("Stego File:");
        sl_panel_5.putConstraint(SpringLayout.WEST, lblStegoFile, 10, SpringLayout.WEST, panel_5);
        sl_panel_5.putConstraint(SpringLayout.SOUTH, lblStegoFile, -6, SpringLayout.SOUTH, panel_5);
        panel_5.add(lblStegoFile);

        textField_4 = new JTextField("", 15);
        sl_panel_5.putConstraint(SpringLayout.WEST, textField_4, 24, SpringLayout.EAST, lblStegoFile);
        sl_panel_5.putConstraint(SpringLayout.SOUTH, textField_4, 0, SpringLayout.SOUTH, lblStegoFile);
        sl_panel_5.putConstraint(SpringLayout.EAST, textField_4, 220, SpringLayout.EAST, lblStegoFile);
        panel_5.add(textField_4);

        button_stegano_open = new JButton("Open");
        button_stegano_open.addActionListener(this);
        sl_panel_5.putConstraint(SpringLayout.NORTH, button_stegano_open, 0, SpringLayout.NORTH, textField_4);
        sl_panel_5.putConstraint(SpringLayout.WEST, button_stegano_open, 26, SpringLayout.EAST, textField_4);
        sl_panel_5.putConstraint(SpringLayout.EAST, button_stegano_open, 99, SpringLayout.EAST, textField_4);
        panel_5.add(button_stegano_open);

        button_stegano_delete = new JButton("Delete");
        button_stegano_delete.addActionListener(this);
        sl_panel_5.putConstraint(SpringLayout.NORTH, button_stegano_delete, 0, SpringLayout.NORTH, textField_4);
        sl_panel_5.putConstraint(SpringLayout.WEST, button_stegano_delete, 15, SpringLayout.EAST, button_stegano_open);
        sl_panel_5.putConstraint(SpringLayout.EAST, button_stegano_delete, 0, SpringLayout.EAST, panel_5);
        panel_5.add(button_stegano_delete);

        panel_8 = new JPanel();
        panel_8.setBackground(Color.WHITE);
        panel_8.setBorder(new TitledBorder(null, "Text:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_8.setBounds(10, 263, 478, 192);
        panel_3.add(panel_8);
        panel_8.setLayout(null);

        textArea = new TextArea();
        textArea.setBounds(10, 22, 458, 160);
        panel_8.add(textArea);

        panel_6 = new JPanel();
        panel_6.setBackground(Color.WHITE);
        panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Original Image", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_6.setBounds(597, 11, 322, 316);
        contentPane.add(panel_6);
        panel_6.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 22, 280, 283);
        panel_6.add(scrollPane);

        lblNewLabel_1 = new JLabel("",SwingConstants.CENTER);
        lblNewLabel_1.setIcon(null);
        scrollPane.setViewportView(lblNewLabel_1);

        panel_7 = new JPanel();
        panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Stego Image", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_7.setBackground(Color.WHITE);
        panel_7.setBounds(597, 341, 322, 316);
        contentPane.add(panel_7);
        panel_7.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(21, 22, 280, 283);
        panel_7.add(scrollPane_1);

        lblNewLabel_3 = new JLabel("",JLabel.CENTER);
        lblNewLabel_3.setIcon(null);
        scrollPane_1.setViewportView(lblNewLabel_3);

        //body ends
    }

    void addMenuBar() {
        mb = new JMenuBar();
        setJMenuBar(mb);
        file = new JMenu("File");
        file.add(fopen = new JMenu("Open..."));
        fopen.add(f1 = new JMenuItem("Image for encoding", new ImageIcon("images/open2.gif")));
        f1.setMnemonic(KeyEvent.VK_O);
        f1.addActionListener(this);
        fopen.add(f2 = new JMenuItem("Image for decoding", new ImageIcon("images/txticon3.gif")));
        f2.setMnemonic(KeyEvent.VK_T);
        f2.addActionListener(this);
        
        file.add(f8 = new JMenuItem("Close", new ImageIcon("images/stop.gif")));
        f8.setMnemonic(KeyEvent.VK_C);
        f8.addActionListener(this);
        file.addSeparator();
        file.add(f3 = new JMenuItem("Save", new ImageIcon("images/save.gif")));
        f3.setMnemonic(KeyEvent.VK_S);
        f3.addActionListener(this);
        f3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        file.add(f4 = new JMenuItem("Save As...", new ImageIcon("images/saveas.gif")));
        f4.setMnemonic(KeyEvent.VK_A);
        f4.addActionListener(this);
        file.addSeparator();
        file.add(f6 = new JMenuItem("Exit", new ImageIcon("images/close.gif")));
        f6.setMnemonic(KeyEvent.VK_X);
        f6.addActionListener(this);
        file.setMnemonic(KeyEvent.VK_F);
        mb.add(file);
//        edit = new JMenu("Edit");
//        edit.setMnemonic(KeyEvent.VK_E);
//        
//        edit.add(e1 = new JMenuItem("Cut", new ImageIcon("images/cut.gif")));
//        e1.setMnemonic(KeyEvent.VK_T);
//        e1.addActionListener(this);
//        e1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
//        edit.add(e2 = new JMenuItem("Copy", new ImageIcon("images/copy.gif")));
//        e2.setMnemonic(KeyEvent.VK_C);
//        e2.addActionListener(this);
//        e2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
//        edit.add(e3 = new JMenuItem("Paste", new ImageIcon("images/paste.gif")));
//        e3.setMnemonic(KeyEvent.VK_P);
//        e3.addActionListener(this);
//        e3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
//        edit.addSeparator();
//        edit.add(e4 = new JMenuItem("Delete", new ImageIcon("images/close.gif")));
//        e4.setMnemonic(KeyEvent.VK_L);
//        e4.addActionListener(this);
//        e4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
//        edit.add(e5 = new JMenuItem("Select All", new ImageIcon("images/txticon1.gif")));
//        e5.setMnemonic(KeyEvent.VK_A);
//        e5.addActionListener(this);
//        e5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
//        mb.add(edit);
        tools = new JMenu("Tools");
        tools.add(t1 = new JMenuItem("Encode", new ImageIcon("images/wheel.gif")));
        t1.setMnemonic(KeyEvent.VK_M);
        t1.addActionListener(this);
        t1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        tools.add(t2 = new JMenuItem("Decode", new ImageIcon("images/wheel.gif")));
        t2.setMnemonic(KeyEvent.VK_R);
        t2.addActionListener(this);
        t2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        tools.addSeparator();
        tools.add(t3 = new JMenuItem("Properties", new ImageIcon("images/display.gif")));
        t3.setMnemonic(KeyEvent.VK_P);
        t3.addActionListener(this);
        t3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        ButtonGroup group2 = new ButtonGroup();
        tools.setMnemonic(KeyEvent.VK_O);
        mb.add(tools);
        JMenuItem h1, h2;
        help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
//        help.add(h1 = new JMenuItem("Steganography Help", new ImageIcon("images/help.gif")));
//        h1.setMnemonic(KeyEvent.VK_S);
//        h1.addActionListener(this);
        help.add(h2 = new JMenuItem("About...", new ImageIcon("images/information.gif")));
        h2.setMnemonic(KeyEvent.VK_A);
        h2.addActionListener(this);
        mb.add(help);
        f2.setEnabled(true);
        f3.setEnabled(false);
        f4.setEnabled(false);
//        f7.setEnabled(false);
        t1.setEnabled(false);
        t2.setEnabled(false);
        t3.setEnabled(false);

    }

    public void actionPerformed(ActionEvent e) {

        try {
            String s;
            s = e.getActionCommand();

            if (s.equals("Open")) {
                
                if (e.getSource() == button_img_open) {
                        
                        if(lblNewLabel_3.getIcon() != null)
                        {
                            JOptionPane.showMessageDialog(this, "Press Clear All button", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                }
                
                if (e.getSource() == button_stegano_open) {
                        
                        if(lblNewLabel_1.getIcon() != null)
                        {
                            JOptionPane.showMessageDialog(this, "Press Clear All button", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                }

                fd1 = new FileDialog(this, "open", FileDialog.LOAD);

                fd1.setDirectory(dn);
                fd1.setVisible(true);

                dn = fd1.getDirectory();
                fn = fd1.getFile();

                if (fn != null) {

                    path = dn + fn;
                    url = "file:///" + path;
                    ico = new ImageIcon(new URL(url));

                    t3.setEnabled(true);

                    if (e.getSource() == button_img_open) {
                        
                        
                        
                        File file = new File(path);

                        epath = path;
                        this.setTitle(fn + " - Steganography");
                        textField.setText(path);
                        textField_1.setText(Utils.getExtension(file));
                        lblNewLabel_1.setIcon(ico);
                        
                        t1.setEnabled(true);
                        t2.setEnabled(false);

                    } else if (e.getSource() == button_data_open) {
                        tpath = path;

                        textField_2.setText(tpath);

                        textArea.replaceRange("", 0, textArea.getText().length());

                        try {
                            FileInputStream fileInputStream = new FileInputStream(tpath);
                            int ch;
                            while ((ch = fileInputStream.read()) != -1) {
                                textArea.append(String.valueOf((char) ch));
                            }
                            fileInputStream.close();

                        } catch (Exception error) {
                            error.printStackTrace();
                        }

                    } else if (e.getSource() == button_stegano_open) {
                        
                        
                        File file = new File(path);

                        epath = path;
                        this.setTitle(fn + " - Steganography");
                        textField_4.setText(path);

                        lblNewLabel_3.setIcon(ico);
                        
                        t1.setEnabled(false);
                        t2.setEnabled(true);

                    }

                }

            } else if (s.equals("Encode")) {
                if (lblNewLabel_1.getIcon() == null) {
                    JOptionPane.showMessageDialog(this, "Select an image first", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(lblNewLabel_3.getIcon() != null)
                {
                    JOptionPane.showMessageDialog(this, "Press Clear All button", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                pe = new Process((ImageIcon) lblNewLabel_1.getIcon());
                boolean success = pe.setTextBuffer(SPanel.getBuffer(textArea));
                if (success) {
                    f3.setEnabled(true);
                    f4.setEnabled(true);
//                    if (ipath != null) {
//                        pe.setImage(p1.getImage(), new Utils().getExtension(new File(ipath)));
//                    }

                    lblNewLabel_3.setIcon(pe.mergeData());
                    System.out.println("Message merged successfully....");
//                    JOptionPane.showMessageDialog(this,"Message merged successfully....","Success",JOptionPane.PLAIN_MESSAGE);
//                    pwdPassword.setText("");
//                    
//                    pd.setPassword("");

                }
            } else if (s.equals("Decode")) {
                
                if(lblNewLabel_3.getIcon() == null)
                {
                    JOptionPane.showMessageDialog(this, "Select stego file first", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if(lblNewLabel_1.getIcon() != null)
                {
                    JOptionPane.showMessageDialog(this, "Press Clear All button", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                f3.setEnabled(true);
                f4.setEnabled(true);

                //path = null;
//                ico = p2.getCoverImage();
                pd = new Process(ico);
                //String str=pd.getText();
                //p2.setText(str);
                int cbuf[] = pd.getBuffer();

                s = "";

                for (int i : cbuf) {
                    s += (Character.toString((char) i));
                }

                textArea.setText(s);

            } else if (s.equals("Clear All\r\n")) {

                lblNewLabel_1.setIcon(null);
                lblNewLabel_1.revalidate();

                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
                textArea.replaceRange("", 0, textArea.getText().length());

                lblNewLabel_3.setIcon(null);
                lblNewLabel_3.revalidate();

                textField_4.setText("");
                
                
                this.setTitle("Steganography");
                
                
                
                f2.setEnabled(true);
                f3.setEnabled(false);
                f4.setEnabled(false);
                
                f8.setEnabled(false);
                t1.setEnabled(false);
                t2.setEnabled(false);
                t3.setEnabled(false);

                ipath = null;
                epath = null;
                path = null;
                tpath = null;

            }
            else if (s.equals("Delete")) {

                if (e.getSource() == button_img_delete) {

                    epath = "";
                    this.setTitle("Steganography");

                    textField.setText("");
                    textField_1.setText(epath);
                    lblNewLabel_1.setIcon(null);
                    ico = null;
                    
                    ClearAll();

                } else if (e.getSource() == button_data_delete) {
                    tpath = "";

                    textField_2.setText(tpath);

                    textArea.replaceRange("", 0, textArea.getText().length());

                } else if (e.getSource() == button_stegano_delete) {

                    epath = "";
                    this.setTitle("Steganography");
                    textField_4.setText(epath);

                    lblNewLabel_3.setIcon(null);
                    ico = null;
                    
                    ClearAll();

                }

            }
            else if (s.equals("Image for encoding")) {
                
                if(lblNewLabel_3.getIcon() != null)
                {
                    JOptionPane.showMessageDialog(this, "Press Clear All button", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                
                
                fd1 = new FileDialog(this, "open", FileDialog.LOAD);

                fd1.setDirectory(dn);
                fd1.setVisible(true);

                dn = fd1.getDirectory();
                fn = fd1.getFile();

                if (fn != null) {

                    path = dn + fn;
                    url = "file:///" + path;
                    ico = new ImageIcon(new URL(url));

                    t1.setEnabled(true);
                    t3.setEnabled(true);

                   
                    File file = new File(path);

                    epath = path;
                    this.setTitle(fn + " - Steganography");
                    textField.setText(path);
                    textField_1.setText(Utils.getExtension(file));
                    lblNewLabel_1.setIcon(ico);

                    
                }
            }
            else if(s.equals("Image for decoding"))
            {
                
                if(lblNewLabel_1.getIcon() != null)
                {
                    JOptionPane.showMessageDialog(this, "Press Clear All button", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                fd1 = new FileDialog(this, "open", FileDialog.LOAD);

                fd1.setDirectory(dn);
                fd1.setVisible(true);

                dn = fd1.getDirectory();
                fn = fd1.getFile();

                if (fn != null) {

                    path = dn + fn;
                    url = "file:///" + path;
                    ico = new ImageIcon(new URL(url));

                        
                        File file = new File(path);

                        epath = path;
                        this.setTitle(fn + " - Steganography");
                        textField_4.setText(path);

                        lblNewLabel_3.setIcon(ico);
                        
                        t1.setEnabled(false);
                        t2.setEnabled(true);


                    
                }
            }
            else if (s.equals("Close")) {
                    ClearAll();
            }

            if (s.equals("Save") || s.equals("Save As...")) {

                fd2 = new FileDialog(this, "Save", FileDialog.SAVE);
                fd2.setVisible(true);
                dn = fd2.getDirectory();
                fn = fd2.getFile();
                path = dn + fn;

                if (fn != null) {

//                    if (t.getSelectedIndex() == 0) {
                    ico = (ImageIcon) lblNewLabel_3.getIcon();
                    ps = new Process(ico);
                    ps.saveImage(ico, path, new Utils().getExtension(new File(epath)), "png");
                    
                }

            }

            
            if (s.equals("Properties")) {
                final JDialog d;
                d = new JDialog(this, "Properties");
                t = new JTabbedPane();
                ico = new ImageIcon("images/wheel.gif");

                Properties pr1 = new Properties(d, epath);
                JPanel p1 = pr1.getPanel();
                t.addTab("Cover Image", ico, p1);

                if (ipath != null) {
                    Properties pr2 = new Properties(d, ipath);
                    JPanel p2 = pr2.getPanel();
                    t.addTab("Image", ico, p2);
                }

                d.getContentPane().add(t);
                d.setVisible(true);
            }

            if (s.equals("About...")) {

                JOptionPane.showMessageDialog(this, "   Steganography 1.0",
                        "About Steganography...",
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("images/butterfly48.png"));
            }

            if (s.equals("Exit")) {
                System.exit(0);
            }

        } catch (Exception er) {
            System.out.println("Stegano ERROR::" + er.getMessage());
        }
    }
    
    void ClearAll()
    {
        
                this.setTitle("Steganography");
                
                lblNewLabel_1.setIcon(null);
                lblNewLabel_1.revalidate();

                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
                textArea.replaceRange("", 0, textArea.getText().length());

                lblNewLabel_3.setIcon(null);
                lblNewLabel_3.revalidate();

                textField_4.setText("");
                
                f2.setEnabled(true);
                f3.setEnabled(false);
                f4.setEnabled(false);
                f8.setEnabled(false);
                t1.setEnabled(false);
                t2.setEnabled(false);
                t3.setEnabled(false);

                ipath = null;
                epath = null;
                path = null;
                tpath = null;
    }

}

