package UI;

import control.SellControl;
import util.Util;
import javax.swing.*;
import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Date;

import static java.awt.Label.CENTER;


public class SellView extends JFrame implements ActionListener{
    ArrayList<JButton> calculator = new ArrayList<>();
    JComboBox<String> canteen   = new JComboBox<>();
    JComboBox<String> machine   = new JComboBox<>();
    JLabel balance = new JLabel("", SwingConstants.CENTER);
    JButton upload = new JButton("上传数据");
    JButton starter = new JButton("开/关");
    JButton cardOp = new JButton("点击插卡");
    SellControl control;
    boolean isInsertCard = false;
    boolean isOpen = false;

    public SellView(SellControl control){
        this.control = control;
        setFrame();
        initListener();
    }

    public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();
        if (o == cardOp) {
            updateCardState();
            setInsertCardState(isInsertCard);
            if (isInsertCard) {
                showCardData();
            } else {
                control.updateCardData();
                control.uploadData();
                balance.setText("");
            }
        } else if (o == starter) {
            isOpen = !isOpen;
            setOpenState(isOpen);
        } else if (o == upload) {
           control.uploadData();
        } else if (isInputEvent(o)) {
            String input = ae.getActionCommand();
            control.inputCost(input);
        }
    }

    private void setFrame(){
        setTitle("售饭机界面");
        setSize(750, 550);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);


        JPanel input = inputPanel();
        add(input, BorderLayout.CENTER);
        JPanel machine = machineSelect();
        add(machine, BorderLayout.EAST);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        setOpenState(false);
        setInsertCardState(false);
    }

    private void setOpenState(boolean state) {
        cardOp.setEnabled(state);
        canteen.setEnabled(state);
        machine.setEnabled(state);
    }

    private void setInsertCardState(boolean state) {
        for (JButton b: calculator) {
            b.setEnabled(state);
        }
        upload.setEnabled(state);
        balance.setEnabled(state);
    }

    private JPanel machineSelect() {
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(200, 200));
        p.setSize(500, 550);
        p.setLayout(new GridLayout(6, 1));
        p.add(new JLabel("设置食堂"));
        canteen.addItem("食堂1");
        canteen.addItem("食堂2");
        canteen.addItem("食堂3");
        p.add(canteen);
        p.add(new JLabel("设置机号"));
        machine.addItem("机器1");
        machine.addItem("机器2");
        machine.addItem("机器3");
        p.add(machine);
        p.add(upload);
        p.add(starter);

        return p;
    }

    private JPanel inputPanel() {
        JButton b0 = new JButton("0");
        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        JButton b7 = new JButton("7");
        JButton b8 = new JButton("8");
        JButton b9 = new JButton("9");
        JButton plus = new JButton("+");
        JButton minus = new JButton("-");
        JButton dot = new JButton(".");

        calculator.add(b1);
        calculator.add(b2);
        calculator.add(b3);
        calculator.add(plus);
        calculator.add(b4);
        calculator.add(b5);
        calculator.add(b6);
        calculator.add(minus);
        calculator.add(b7);
        calculator.add(b8);
        calculator.add(b9);
        calculator.add(dot);
        calculator.add(b0);


        JPanel input = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        input.setLayout(gridbag);
        c.insets = new Insets(10, 10, 10, 10);
        c.ipadx = 50;
        c.ipady = 50;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;

        c.gridwidth = GridBagConstraints.REMAINDER;
        balance.setForeground(Color.GREEN);
        balance.setBackground(Color.WHITE);
        balance.setOpaque(true);
        balance.setFont(new Font("Serif", Font.PLAIN, 40));
        gridbag.setConstraints(balance, c);
        input.add(balance);

        int i = 1;
        for (JButton b: calculator) {
            if (i % 4 == 0) {
                c.gridwidth = GridBagConstraints.REMAINDER;
            } else {
                c.gridwidth = 1;
            }
            gridbag.setConstraints(b, c);
            input.add(b);
            i++;
        }
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(cardOp, c);
        input.add(cardOp);
        return input;
    }

    private void initListener(){
        canteen.addActionListener(this);
        machine.addActionListener(this);
        upload.addActionListener(this);
        starter.addActionListener(this);
        cardOp.addActionListener(this);
        for (JButton b: calculator) {
            b.addActionListener(this);
        }
    }

    private boolean isInputEvent(Object o) {
        return calculator.contains(o);
    }

    private void updateCardState() {
        isInsertCard = !isInsertCard;
        if (isInsertCard == true) {
            cardOp.setText("点击拔卡");
        } else {
            cardOp.setText("点击插卡");
        }
    }

    private boolean isInsertCardOp() {
        return isInsertCard == true;
    }

    public String getCanteen() {
        String item = canteen.getSelectedItem().toString();
        String id = item.substring(2);
        return id;
    }

    public String getMachine() {
        String item = machine.getSelectedItem().toString();
        String id = item.substring(2);
        return id;
    }

    public void showCardData() {
        String id = control.getCardId();
        String b = control.getBalance(id);
        balance.setText(b);
    }
}
