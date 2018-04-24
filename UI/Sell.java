// package UI;
//
// import util.Util;
// import javax.swing.*;
// import javax.xml.stream.*;
// import javax.xml.stream.events.XMLEvent;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.util.ArrayList;
// import java.awt.Color;
// import java.util.Date;
//
// import static canteen.Util1.log;
// import static java.awt.Label.CENTER;
//
//
//
// public class Sell extends JFrame implements ActionListener{
//
//
//     // ArrayList<JButton> calculator = new ArrayList<>();
//     // JComboBox<String> canteen   = new JComboBox<>();
//     // JComboBox<String> machine   = new JComboBox<>();
//     // JLabel balance = new JLabel("请插卡", SwingConstants.CENTER);
//     // JButton upload = new JButton("上传数据");
//     // JButton starter = new JButton("开/关");
//     // JButton cardOp = new JButton("点击插卡");
//
//     // ArrayList<String> inputBuffer = new ArrayList<>();
//     boolean isInsertCard = false;
//     int cardId = 0;
//     String xmlPath = "configure.xml";
//
//
//
//
// //    public Sell(){
// //        setFrame();
// //        initListener();
// //    }
//
//     // private void setFrame(){
//     //     setTitle("售饭机界面");
//     //     setSize(750, 550);
//     //     setLayout(new BorderLayout());
//     //     setLocationRelativeTo(null);
//     //
//     //
//     //     JPanel input = inputPanel();
//     //     add(input, BorderLayout.CENTER);
//     //     JPanel machine = machineSelect();
//     //     add(machine, BorderLayout.EAST);
//     //
//     //     setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//     //     setVisible(true);
//     // }
//
//     // private JPanel machineSelect() {
//     //     JPanel p = new JPanel();
//     //     p.setPreferredSize(new Dimension(200, 200));
//     //     p.setSize(500, 550);
//     //     p.setLayout(new GridLayout(6, 1));
//     //     p.add(new JLabel("设置食堂"));
//     //     canteen.addItem("食堂1");
//     //     canteen.addItem("食堂2");
//     //     canteen.addItem("食堂3");
//     //     p.add(canteen);
//     //     p.add(new JLabel("设置机号"));
//     //     machine.addItem("机器1");
//     //     machine.addItem("机器2");
//     //     machine.addItem("机器3");
//     //     p.add(machine);
//     //     p.add(upload);
//     //     p.add(starter);
//     //
//     //     return p;
//     // }
//     //
//     // private JPanel inputPanel() {
//     //     JButton b0 = new JButton("0");
//     //     JButton b1 = new JButton("1");
//     //     JButton b2 = new JButton("2");
//     //     JButton b3 = new JButton("3");
//     //     JButton b4 = new JButton("4");
//     //     JButton b5 = new JButton("5");
//     //     JButton b6 = new JButton("6");
//     //     JButton b7 = new JButton("7");
//     //     JButton b8 = new JButton("8");
//     //     JButton b9 = new JButton("9");
//     //     JButton plus = new JButton("+");
//     //     JButton minus = new JButton("-");
//     //     JButton dot = new JButton(".");
//     //
//     //     calculator.add(b1);
//     //     calculator.add(b2);
//     //     calculator.add(b3);
//     //     calculator.add(plus);
//     //     calculator.add(b4);
//     //     calculator.add(b5);
//     //     calculator.add(b6);
//     //     calculator.add(minus);
//     //     calculator.add(b7);
//     //     calculator.add(b8);
//     //     calculator.add(b9);
//     //     calculator.add(dot);
//     //     calculator.add(b0);
//     //
//     //     JPanel input = new JPanel();
//     //     GridBagLayout gridbag = new GridBagLayout();
//     //     GridBagConstraints c = new GridBagConstraints();
//     //     input.setLayout(gridbag);
//     //     c.insets = new Insets(10, 10, 10, 10);
//     //     c.ipadx = 50;
//     //     c.ipady = 50;
//     //     c.fill = GridBagConstraints.BOTH;
//     //     c.weightx = 1.0;
//     //
//     //     c.gridwidth = GridBagConstraints.REMAINDER;
//     //     balance.setForeground(Color.GREEN);
//     //     balance.setBackground(Color.WHITE);
//     //     balance.setOpaque(true);
//     //     balance.setFont(new Font("Serif", Font.PLAIN, 40));
//     //     gridbag.setConstraints(balance, c);
//     //     input.add(balance);
//     //
//     //     int i = 1;
//     //     for (JButton b: calculator) {
//     //         if (i % 4 == 0) {
//     //             c.gridwidth = GridBagConstraints.REMAINDER;
//     //         } else {
//     //             c.gridwidth = 1;
//     //         }
//     //         gridbag.setConstraints(b, c);
//     //         input.add(b);
//     //         i++;
//     //     }
//     //     c.gridwidth = GridBagConstraints.REMAINDER;
//     //     gridbag.setConstraints(cardOp, c);
//     //     input.add(cardOp);
//     //     return input;
//     // }
//
//     // private void initListener(){
//     //     canteen.addActionListener(this);
//     //     machine.addActionListener(this);
//     //     upload.addActionListener(this);
//     //     starter.addActionListener(this);
//     //     cardOp.addActionListener(this);
//     //     for (JButton b: calculator) {
//     //         b.addActionListener(this);
//     //     }
//     // }
//
//     // public void actionPerformed(ActionEvent ae) {
//     //     Object o = ae.getSource();
//     //     if (o == cardOp) {
//     //         if (isInsertCard()) {
//     //             updateCardData();
//     //         } else {
//     //             showCardData();
//     //         }
//     //         updateCardState();
//     //
//     //     } else {
//     //         if (isInsertCard()) {
//     //             if (calculator.contains(o)) {
//     //                 String text = ae.getActionCommand();
//     //                 inputBuffer.add(text);
//     //             }
//     //         }
//     //
//     //     }
//     //
//     // }
//
//     // private void updateCardState() {
//     //     isInsertCard = !isInsertCard;
//     //     if (isInsertCard == true) {
//     //         cardOp.setText("点击拔卡");
//     //     } else {
//     //         cardOp.setText("点击插卡");
//     //     }
//     // }
//
//     // private void showCardData() {
//     //     balance.setText("100000");
//     // }
//
//     // private void updateCardData() {
//     //     if (inputBuffer.size() > 0) {
//     //         double cost = getCost();
//     //         String canteenId = canteen.getSelectedItem().toString();
//     //         String machineId = machine.getSelectedItem().toString();
//     //         insertXml(cost, canteenId, machineId);
//     //         String cost_ = String.valueOf(cost);
//     //         balance.setText(cost_);
//     //     } else {
//     //         balance.setText("请点击插卡");
//     //     }
//     //
//     // }
//
//     // private void insertXml(double cost, String canteenId, String machineId) {
//     //     File file = new File(xmlPath);
//     //     boolean empty = !file.exists() || file.length() == 0;
//     //     try {
//     //         if (empty) {
//     //             initXml();
//     //         }
//     //         appendXml(cost, canteenId, machineId);
//     //     } catch(Exception e) {
//     //         log(e);
//     //     }
//     // }
//     //
//     // private void appendXml(double cost, String canteenId, String machineId) throws Exception{
//     //     XMLEventReader eventReader =
//     //             XMLInputFactory.newInstance().createXMLEventReader(new FileReader(xmlPath));
//     //     XMLEventWriter writer =
//     //             XMLOutputFactory.newInstance().createXMLEventWriter(new FileWriter(xmlPath));
//     //     while (eventReader.hasNext()) {
//     //         XMLEvent event = eventReader.nextEvent();
//     //         if (!isLastRecord(event)) {
//     //             writer.add(event);
//     //         } else {
//     //             insertRecord(writer, cost, canteenId, machineId);
//     //             writer.add(event);
//     //         }
//     //     }
//     //     writer.flush();
//     //     writer.close();
//     // }
//     //
//     // private void initXml() throws Exception{
//     //     XMLEventFactory eventFactory = XMLEventFactory.newInstance();
//     //     XMLEventWriter writer =
//     //                     XMLOutputFactory.newInstance().createXMLEventWriter(new FileWriter(xmlPath));
//     //     writer.add(eventFactory.createStartDocument());
//     //     writer.add(eventFactory.createStartElement("", "", "consumption"));
//     //     writer.add(eventFactory.createEndElement("", "", "consumption"));
//     //     writer.add(eventFactory.createEndDocument());
//     //     writer.flush();
//     //     writer.close();
//     // }
//     //
//     // private boolean isLastRecord(XMLEvent event) {
//     //     if (event.isEndElement()) {
//     //         String tag = event.asEndElement().getName().toString();
//     //         if (tag.equals("consumption")) {
//     //             return true;
//     //         }
//     //     }
//     //     return false;
//     // }
//     //
//     // private void insertRecord(XMLEventWriter writer, double cost, String canteenId, String machineId) throws Exception{
//     //     String time = new Date().toString();
//     //     String cost_ = String.valueOf(cost);
//     //     String cardId_ = String.valueOf(cardId);
//     //     XMLEventFactory factory = XMLEventFactory.newInstance();
//     //
//     //     writer.add(factory.createStartElement("", "", "record"));
//     //     addElement(writer, factory, "card", cardId_);
//     //     addElement(writer, factory, "canteen", canteenId);
//     //     addElement(writer, factory, "machine", machineId);
//     //     addElement(writer, factory, "cost", cost_);
//     //     addElement(writer, factory, "time", time);
//     //     writer.add(factory.createEndElement("", "", "record"));
//     // }
//     //
//     // private void addElement(XMLEventWriter writer, XMLEventFactory factory, String tagName, String value) throws Exception{
//     //     writer.add(factory.createStartElement("", "", tagName));
//     //     writer.add(factory.createCharacters(value));
//     //     writer.add(factory.createEndElement("", "", tagName));
//     // }
//
// //    private void flushBalance() {
// //        double cost = getCost();
// //        String text = String.valueOf(cost);
// //        balance.setText(text);
// //    }
//
//     // private double getCost() {
//     //     double cost = getNumber();
//     //     while (true) {
//     //         if (inputBuffer.isEmpty()) {
//     //             return cost;
//     //         }
//     //         String c = inputBuffer.remove(0);
//     //         if (c.equals("+")) {
//     //             double n = getNumber();
//     //             cost += n;
//     //         } else if (c.equals("-")) {
//     //             double n = getNumber();
//     //             cost -= n;
//     //         } else {
//     //             throw new java.lang.Error("not + or - " + c);
//     //         }
//     //     }
//     // }
//     //
//     // private double getNumber() {
//     //     double n = 0;
//     //     while (true) {
//     //         if (inputBuffer.isEmpty()) {
//     //             return n;
//     //         }
//     //         String c = inputBuffer.remove(0);
//     //         if (c.equals("+") || c.equals("-")) {
//     //             inputBuffer.add(0, c);
//     //             return n;
//     //         }
//     //         if (c.equals(".")) {
//     //             double decimal = getDecimal();
//     //             n = n + decimal;
//     //             return n;
//     //         }
//     //         int digit = Integer.parseInt(c);
//     //         n = n * 10 + digit;
//     //     }
//     // }
//     //
//     // private double getDecimal() {
//     //     double decimal = 0;
//     //     while (true) {
//     //         if (inputBuffer.isEmpty()) {
//     //             return decimal;
//     //         }
//     //         String c = inputBuffer.remove(0);
//     //         if (c.equals("+") || c.equals("-")) {
//     //             inputBuffer.add(0, c);
//     //             return decimal;
//     //         }
//     //         int digit = Integer.parseInt(c);
//     //         decimal = decimal + digit * 0.1;
//     //     }
//     // }
//
//     private boolean isInsertCard() {
//         return isInsertCard == true;
//     }
//
// }
//
//
//
// // class SellView extends JFrame implements ActionListener{
// //     ArrayList<JButton> calculator = new ArrayList<>();
// //     JComboBox<String> canteen   = new JComboBox<>();
// //     JComboBox<String> machine   = new JComboBox<>();
// //     JLabel balance = new JLabel("请插卡", SwingConstants.CENTER);
// //     JButton upload = new JButton("上传数据");
// //     JButton starter = new JButton("开/关");
// //     JButton cardOp = new JButton("点击插卡");
// //     SellControl control;
// //
// //     public SellView(SellControl control){
// //         this.control = control;
// //         setFrame();
// //         initListener();
// //     }
// //
// //     private void setFrame(){
// //         setTitle("售饭机界面");
// //         setSize(750, 550);
// //         setLayout(new BorderLayout());
// //         setLocationRelativeTo(null);
// //
// //
// //         JPanel input = inputPanel();
// //         add(input, BorderLayout.CENTER);
// //         JPanel machine = machineSelect();
// //         add(machine, BorderLayout.EAST);
// //
// //         setDefaultCloseOperation(DISPOSE_ON_CLOSE);
// //         setVisible(true);
// //     }
// //
// //     private JPanel machineSelect() {
// //         JPanel p = new JPanel();
// //         p.setPreferredSize(new Dimension(200, 200));
// //         p.setSize(500, 550);
// //         p.setLayout(new GridLayout(6, 1));
// //         p.add(new JLabel("设置食堂"));
// //         canteen.addItem("食堂1");
// //         canteen.addItem("食堂2");
// //         canteen.addItem("食堂3");
// //         p.add(canteen);
// //         p.add(new JLabel("设置机号"));
// //         machine.addItem("机器1");
// //         machine.addItem("机器2");
// //         machine.addItem("机器3");
// //         p.add(machine);
// //         p.add(upload);
// //         p.add(starter);
// //
// //         return p;
// //     }
// //
// //     private JPanel inputPanel() {
// //         JButton b0 = new JButton("0");
// //         JButton b1 = new JButton("1");
// //         JButton b2 = new JButton("2");
// //         JButton b3 = new JButton("3");
// //         JButton b4 = new JButton("4");
// //         JButton b5 = new JButton("5");
// //         JButton b6 = new JButton("6");
// //         JButton b7 = new JButton("7");
// //         JButton b8 = new JButton("8");
// //         JButton b9 = new JButton("9");
// //         JButton plus = new JButton("+");
// //         JButton minus = new JButton("-");
// //         JButton dot = new JButton(".");
// //
// //         calculator.add(b1);
// //         calculator.add(b2);
// //         calculator.add(b3);
// //         calculator.add(plus);
// //         calculator.add(b4);
// //         calculator.add(b5);
// //         calculator.add(b6);
// //         calculator.add(minus);
// //         calculator.add(b7);
// //         calculator.add(b8);
// //         calculator.add(b9);
// //         calculator.add(dot);
// //         calculator.add(b0);
// //
// //         JPanel input = new JPanel();
// //         GridBagLayout gridbag = new GridBagLayout();
// //         GridBagConstraints c = new GridBagConstraints();
// //         input.setLayout(gridbag);
// //         c.insets = new Insets(10, 10, 10, 10);
// //         c.ipadx = 50;
// //         c.ipady = 50;
// //         c.fill = GridBagConstraints.BOTH;
// //         c.weightx = 1.0;
// //
// //         c.gridwidth = GridBagConstraints.REMAINDER;
// //         balance.setForeground(Color.GREEN);
// //         balance.setBackground(Color.WHITE);
// //         balance.setOpaque(true);
// //         balance.setFont(new Font("Serif", Font.PLAIN, 40));
// //         gridbag.setConstraints(balance, c);
// //         input.add(balance);
// //
// //         int i = 1;
// //         for (JButton b: calculator) {
// //             if (i % 4 == 0) {
// //                 c.gridwidth = GridBagConstraints.REMAINDER;
// //             } else {
// //                 c.gridwidth = 1;
// //             }
// //             gridbag.setConstraints(b, c);
// //             input.add(b);
// //             i++;
// //         }
// //         c.gridwidth = GridBagConstraints.REMAINDER;
// //         gridbag.setConstraints(cardOp, c);
// //         input.add(cardOp);
// //         return input;
// //     }
// //
// //     private void initListener(){
// //         canteen.addActionListener(this);
// //         machine.addActionListener(this);
// //         upload.addActionListener(this);
// //         starter.addActionListener(this);
// //         cardOp.addActionListener(this);
// //         for (JButton b: calculator) {
// //             b.addActionListener(this);
// //         }
// //     }
// //
// //     public void actionPerformed(ActionEvent ae) {
// //         Object o = ae.getSource();
// //         if (o == cardOp) {
// //             if (isInsertCard()) {
// //                 control.updateCardData();
// //             } else {
// //                 showCardData();
// //             }
// //             updateCardState();
// //
// //         } else {
// //             if (isInsertCard()) {
// //                 if (calculator.contains(o)) {
// //                     String input = ae.getActionCommand();
// //                     control.inputCost(input);
// //                 }
// //             }
// //
// //         }
// //
// //     }
// //
// //     private void showCardData() {
// //         balance.setText("100000");
// //     }
// //
// //     private void updateCardState() {
// //         isInsertCard = !isInsertCard;
// //         if (isInsertCard == true) {
// //             cardOp.setText("点击拔卡");
// //         } else {
// //             cardOp.setText("点击插卡");
// //         }
// //     // }
// //
// //
// //
// // }
