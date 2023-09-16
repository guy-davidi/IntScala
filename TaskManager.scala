import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.{Button, TableColumn, TableView}
import javafx.scene.layout.{HBox, VBox}
import javafx.stage.Stage
import javafx.beans.property.{SimpleBooleanProperty, SimpleStringProperty}
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.scene.control.cell.CheckBoxTableCell
import javafx.util.Callback

object TaskManager extends Application {
  // Define a mutable list to store tasks
  var tasks: ListBuffer[Task] = ListBuffer()

  // Define JavaFX TableView for displaying tasks
  val tableView: TableView[Task] = new TableView[Task]()
  tableView.setEditable(true)

  // Create columns for the TableView
  val titleCol = new TableColumn[Task, String]("Title")
  titleCol.setCellValueFactory(new Callback[TableColumn.CellDataFeatures[Task, String], ObservableValue[String]] {
    override def call(p: TableColumn.CellDataFeatures[Task, String]): ObservableValue[String] = new SimpleStringProperty(p.getValue.title)
  })

  val descriptionCol = new TableColumn[Task, String]("Description")
  descriptionCol.setCellValueFactory(new Callback[TableColumn.CellDataFeatures[Task, String], ObservableValue[String]] {
    override def call(p: TableColumn.CellDataFeatures[Task, String]): ObservableValue[String] = new SimpleStringProperty(p.getValue.description)
  })

  val dueDateCol = new TableColumn[Task, String]("Due Date")
  dueDateCol.setCellValueFactory(new Callback[TableColumn.CellDataFeatures[Task, String], ObservableValue[String]] {
    override def call(p: TableColumn.CellDataFeatures[Task, String]): ObservableValue[String] = new SimpleStringProperty(p.getValue.dueDate)
  })

  val completedCol = new TableColumn[Task, java.lang.Boolean]("Completed")
  completedCol.setCellValueFactory(new Callback[TableColumn.CellDataFeatures[Task, java.lang.Boolean], ObservableValue[java.lang.Boolean]] {
    override def call(p: TableColumn.CellDataFeatures[Task, java.lang.Boolean]): ObservableValue[java.lang.Boolean] = {
      new SimpleBooleanProperty(p.getValue.completed)
    }
  })

  completedCol.setCellFactory(CheckBoxTableCell.forTableColumn(completedCol))

  tableView.getColumns.addAll(titleCol, descriptionCol, dueDateCol, completedCol)

  // Define JavaFX buttons for interacting with tasks
  val addButton = new Button("Add Task")
  val markCompletedButton = new Button("Mark as Completed")
  val deleteButton = new Button("Delete Task")

  // Define JavaFX UI layout
  val buttonBox = new HBox(10, addButton, markCompletedButton, deleteButton)
  val root = new VBox(10, tableView, buttonBox)
  val scene = new Scene(root)

  // Function to add a task
  def addTask(task: Task): Unit = {
    tasks += task
    tableView.getItems.setAll(FXCollections.observableArrayList(tasks: _*))
  }

  // Function to mark a task as completed
  def markTaskAsCompleted(): Unit = {
    val selectedTask = tableView.getSelectionModel.getSelectedItem
    if (selectedTask != null) {
      selectedTask.completed = true
      tableView.refresh()
    }
  }

  // Function to delete a task
  def deleteTask(): Unit = {
    val selectedTask = tableView.getSelectionModel.getSelectedItem
    if (selectedTask != null) {
      tasks -= selectedTask
      tableView.getItems.setAll(FXCollections.observableArrayList(tasks: _*))
    }
  }

  override def start(primaryStage: Stage): Unit = {
    // Set up event handlers for buttons
    addButton.setOnAction(_ => addTask(Task("New Task", "New Description", "New Due Date")))
    markCompletedButton.setOnAction(_ => markTaskAsCompleted())
    deleteButton.setOnAction(_ => deleteTask())

    // Set up the stage
    primaryStage.setTitle("Task Manager")
    primaryStage.setScene(scene)
    primaryStage.show()
  }

  // Rest of your code (loading and saving tasks) remains the same as in the previous version.

  def main(args: Array[String]): Unit = {
    // Load tasks from a file (if the file exists)
    val fileName = "tasks.txt"
    loadTasksFromFile(fileName)

    // Launch the JavaFX application
    Application.launch(args: _*)

    // Save tasks to a file before exiting
    saveTasksToFile(fileName)
  }
}
