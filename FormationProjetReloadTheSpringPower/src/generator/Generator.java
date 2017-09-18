package generator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import org.apache.commons.io.FileUtils;

public class Generator {
	private static final String PACKAGE_SEARCH = "model";
	private static JTextField valueClass = new JTextField();
	private static JTextArea result = new JTextArea();

	private static JToolBar toolBar = new JToolBar();
	private static String destinationFolder;
	private static String sourceFile;

	final static Set<Class<?>> NUMBER_REFLECTED_PRIMITIVES;
	static {
		Set<Class<?>> s = new HashSet<>();
		s.add(byte.class);
		s.add(short.class);
		s.add(int.class);
		s.add(long.class);
		s.add(float.class);
		s.add(double.class);
		NUMBER_REFLECTED_PRIMITIVES = s;
	}

	static String getGeneric(String generic) {
		Pattern pattern = Pattern.compile("<(.*?)>");
		Matcher matcher = pattern.matcher(generic);

		if (matcher.find()) {
			return matcher.group(1);
		}

		else {
			return generic;
		}

	}

	private static String autoComplete(String text) {
		// TODO Auto-generated method stub
		System.out.println("pass");
		List<Class<?>> classes = ClassFinder.find(PACKAGE_SEARCH);
		int nbOk = 0;
		String className = "";
		for (Class<?> c : classes) {
			if (nameOfClass(c.getName()).startsWith(text)) {
				nbOk++;
				className = c.getName();
			}
		}

		if (nbOk == 1) {
			return className;
		}
		return text;
	}

	static String nameOfClass(String clazz) {
		// TODO Auto-generated method stub
		int endIndex = clazz.lastIndexOf('.');
		if (endIndex != -1) {
			return clazz.substring(endIndex + 1, clazz.length());
		} else {
			return clazz;
		}
	}
	
	private static Matcher getRegexMatcher(String value, String regexList) {
		Pattern pattern = Pattern.compile(regexList);
		Matcher matcher = pattern.matcher(value);
		return matcher;
	}

	public static void main(String[] args) {
		// generateFile(new GenerationFromFile(), Matiere.class, ".java",
		// "webServiceTemplate.javaparse","src/fr/m2i/jerseygen/", "model");
		// generateFile(new
		// HtmlGeneration(),Cursus.class,".html","addTemplate.html","WebContent/jsp/","model");
		// generateFile(new
		// GenerationFromFile(),Utilisateur.class,".jsp","TemplateServlet.javaparse","WebContent/jsp/","model");
		

	
		JFrame genFrame = new JFrame();
		JFrame directoryWherePutChooserFrame = new JFrame();
		JFrame sourceFileChooserFrame = new JFrame();

		JFileChooser fileChooser = new JFileChooser(".");
		JFileChooser directoryWherePutChooser = new JFileChooser(".");
		JFileChooser sourceFileChooser = new JFileChooser(".");

		directoryWherePutChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		
		sourceFileChooserFrame.getContentPane().add(sourceFileChooser);
		
	
		
		directoryWherePutChooser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("test");
				destinationFolder = fileChooser.getCurrentDirectory().getAbsolutePath();
				directoryWherePutChooserFrame.setVisible(false);
				// TODO Auto-generated method stub

			}
		});
		genFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		genFrame.setSize(400, 400);
		genFrame.setVisible(true);
		JButton generateButton = new JButton("Generer");
		
		JButton directoryWherePutChooserFrameButton = new JButton("Choisir dossier de destination");
		JButton sourceFileChooserButton = new JButton("Choisir le fichier source");

		genFrame.getContentPane().setLayout(new BorderLayout());
		genFrame.getContentPane().add(toolBar, BorderLayout.WEST);
		toolBar.setOrientation(JToolBar.VERTICAL);
		toolBar.add(generateButton);
		toolBar.add(sourceFileChooserButton);
		toolBar.add(directoryWherePutChooserFrameButton);
		result.setEditable(false);

		JPanel panel = new JPanel();

		genFrame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(2,1));

		JScrollPane resultPane = new JScrollPane(result);
		panel.add(valueClass);
		panel.add(resultPane);
		panel.validate();

		valueClass.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {

				if (!e.isControlDown()) {
					valueClass.setText(autoComplete(valueClass.getText()));
				}
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		directoryWherePutChooserFrame.getContentPane().add(directoryWherePutChooser);
		sourceFileChooserFrame.getContentPane().add(sourceFileChooser);
		
		sourceFileChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sourceFile = sourceFileChooser.getSelectedFile().getAbsolutePath();
				sourceFileChooserFrame.setVisible(false);;
				// TODO Auto-generated method stub

			}
		});

		


		setFrameVisible(directoryWherePutChooserFrame, directoryWherePutChooserFrameButton);
		setFrameVisible(sourceFileChooserFrame, sourceFileChooserButton);

		generateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					generateFile(new GenerationFromFile(sourceFile), Class.forName(valueClass.getText()),
							 sourceFile, destinationFolder, PACKAGE_SEARCH);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}

	private static void setFrameVisible(JFrame directoryWherePutChooserFrame,
			JButton directoryWherePutChooserFrameButton) {
		directoryWherePutChooserFrameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				directoryWherePutChooserFrame.setVisible(true);
				directoryWherePutChooserFrame.setSize(400, 400);

			}
		});
	}

	private static void generateFile(ParcourableString jsp, Class<?> classT, String sourceFile,
			String targetFolder, String packageName) {

		String elementsToChange = jsp.parcourir(classT, packageName);
		result.setText(elementsToChange);

		
	}

	private static void changePartsOfFile(String pathToTemplate, String pathToChange)
			throws IOException {
		File templateFile = new File(pathToTemplate);
		System.out.println(templateFile.getName());
		File newFile = new File(pathToChange);
		
		String part = FileUtils.readFileToString(templateFile, "UTF-8");
		
		
		
		 FileUtils.writeStringToFile(newFile, part, "UTF-8");
	}

}
