################################################################################
# Makefile for Calendar program
#

JAVABIN		= /usr/j2se/bin
CLASS_DIR	= ./classes
SOURCE_DIR	= ./src
DOC_DIR		= ./docs/apidoc
CP		= .:$(CLASS_DIR):$(CLASSPATH)crimson.jar:jaxp.jar:xalan.jar

PACKAGES	= com.kdbp.calendar \
			com.kdbp.calendar.data \
			com.kdbp.calendar.gui

JAVA		= ${JAVABIN}/java
JAVAC		= ${JAVABIN}/javac
JAVADOC		= ${JAVABIN}/javadoc
JAR		= ${JAVABIN}/jar
RMIC		= ${JAVABIN}/rmic

JFLAGS 		= -classpath $(CP)
JCFLAGS		= -classpath $(CP) -sourcepath $(SOURCE_DIR) -d $(CLASS_DIR) -g
DOCFLAGS	= -link http://java.sun.com/products/jdk/1.3/docs/api/ \
			-public -d $(DOC_DIR) -sourcepath $(SOURCE_DIR) -classpath $(CP)
#DOCFLAGS	= -private -d $(DOC_DIR) -sourcepath $(SOURCE_DIR) -classpath $(CP)
RMICFLAGS	= -classpath $(CLASS_DIR) -sourcepath $(SOURCE_DIR) -d $(CLASS_DIR) -g

# Runs Perl script in file toClass.pl to create .class file list
FILES = $(shell ./toClass.pl)

default: $(subst $(SOURCE_DIR), $(CLASS_DIR), $(FILES:.java=.class))
	
$(CLASS_DIR)/%.class: $(SOURCE_DIR)/%.java
	$(JAVAC) $(JCFLAGS) $<

javadoc:
	$(JAVADOC) $(DOCFLAGS) $(PACKAGES) 

run:
	$(JAVA) $(JFLAGS) com.kdbp.calendar.CalApp

clean:
	rm -rf classes/com
	rm -rf classes/*.class
	rm -rf docs/apidoc/*.html
	rm -rf docs/apidoc/package-list
	rm -rf docs/apidoc/stylesheet.css
	rm -rf docs/apidoc/com
