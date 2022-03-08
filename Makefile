JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=Vaccine.class\
		VaccineArray.class\
		VaccineArrayApp.class\
		BinaryTreeNode.class\
		BTQueueNode.class\
		BTQueue.class\
		BinaryTree.class\
		BinarySearchTree.class\
		VaccineBSTApp.class
		
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class
	
runarray: $(CLASS_FILES)
	 

runbst: $(CLASS_FILES)
	java -cp bin VaccineBSTApp

experiment:
	python3 scripts/experiment.py

summarise:
	python3 scripts/analysis.py
