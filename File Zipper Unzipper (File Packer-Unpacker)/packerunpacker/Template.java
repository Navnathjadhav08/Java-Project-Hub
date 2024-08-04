import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

class ClockLabel extends JLabel implements ActionListener
{
    String type;
    SimpleDateFormat sdf;

    public ClockLabel(String type)
    {
        this.type = type;
        setForeground(Color.green);
        
        switch (type)
        {
            case "date":
                sdf = new SimpleDateFormat(" MMMM dd yyyy");
                setFont(new Font("sans-serif", Font.PLAIN, 12));
                setHorizontalAlignment(SwingConstants.LEFT);
                break;
            case "time":
                sdf = new SimpleDateFormat("hh:mm:ss a");
                setFont(new Font("sans-serif", Font.PLAIN, 40));
                setHorizontalAlignment(SwingConstants.CENTER);
                break;
            case "day":
                sdf = new SimpleDateFormat("EEEE");
                setFont(new Font("sans-serif", Font.PLAIN, 16));
                setHorizontalAlignment(SwingConstants.RIGHT);
                break;
            default:
                sdf = new SimpleDateFormat();
                break;
        }
        
        Timer t = new Timer(1000, this);
        t.start();
    }

    public void actionPerformed(ActionEvent ae)
    {
        Date d = new Date();
        setText(sdf.format(d));
    }
}

class Template extends JFrame implements Serializable, ActionListener
{
    JPanel _header;
    JPanel _content;
    JPanel _top;

    ClockLabel dayLable;
    ClockLabel timeLable;
    ClockLabel dateLable;

    JButton minimize, exit;

    public Template()
    {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        GridBagLayout grid = new GridBagLayout();
        setLayout(grid);

        _top = new JPanel();
        _top.setBackground(Color.LIGHT_GRAY);
        
        _top.setLayout(null);
        
        getContentPane().add(_top, new GridBagConstraints(0, 0, 1, 1, 1, 5, GridBagConstraints.BASELINE, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        _header = new JPanel();
        _header.setLayout(null);
        _header.setBackground(Color.white);

        getContentPane().add(_header, new GridBagConstraints(0, 1, 1, 1, 1, 20, GridBagConstraints.BASELINE, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        _content = new JPanel();
        _content.setLayout(null);
        _content.setBackground(new Color(0, 50, 120));
        JScrollPane jsp = new JScrollPane(_content, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        getContentPane().add(jsp, new GridBagConstraints(0, 2, 1, 1, 1, 75, GridBagConstraints.BASELINE, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        setTitle("Packer-Unpacker");

        Clock();
        CloseAndMin();
    }

    void CloseAndMin()
    {
        minimize = new JButton("-");
        minimize.setBackground(Color.LIGHT_GRAY);
        minimize.setBounds(MAXIMIZED_HORIZ, 0, 45, 20);

        exit = new JButton("X");
        exit.setHorizontalAlignment(SwingConstants.CENTER);
        exit.setBackground(Color.LIGHT_GRAY);
        exit.setHorizontalTextPosition(0);
        exit.setBounds(MAXIMIZED_HORIZ + 45, 0, 45, 20);

        _top.add(minimize);
        _top.add(exit);

        exit.addActionListener(this);
        minimize.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == exit)
        {
            this.setVisible(false);
            System.exit(0);
        }

        if (ae.getSource() == minimize)
        {
            setState(JFrame.ICONIFIED);
        }
    }

    void Clock()
    {
        dateLable = new ClockLabel("date");
        timeLable = new ClockLabel("time");
        dayLable = new ClockLabel("day");

        dateLable.setForeground(Color.blue);
        timeLable.setForeground(Color.blue);
        dayLable.setForeground(Color.blue);

        dayLable.setFont(new Font("Century", Font.BOLD, 15));
        
        dayLable.setBounds(700, 10, 200, 100);
        
        dateLable.setFont(new Font("Century", Font.BOLD, 15));
        dateLable.setBounds(800, -40, 200, 100);

        timeLable.setFont(new Font("Century", Font.BOLD, 15));
        timeLable.setBounds(760, -15, 200, 100);

        _header.add(dateLable);
        _header.add(timeLable);
        _header.add(dayLable);
    }

    void ClockHome()
    {
        dateLable = new ClockLabel("date");
        timeLable = new ClockLabel("time");
        dayLable = new ClockLabel("day");

        dateLable.setForeground(Color.blue);
        timeLable.setForeground(Color.blue);
        dayLable.setForeground(Color.blue);
        dayLable.setFont(new Font("Century", Font.BOLD, 15));
        dayLable.setBounds(200, 20, 200, 100);
        dateLable.setFont(new Font("Century", Font.BOLD, 15));
        dateLable.setBounds(300, -40, 200, 100);

        timeLable.setFont(new Font("Century", Font.BOLD, 15));
        timeLable.setBounds(260, -10, 200, 100);

        _header.add(dateLable);
        _header.add(timeLable);
        _header.add(dayLable);
    }
}
