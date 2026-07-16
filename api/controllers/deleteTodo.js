const Todo = require("../model/schema");

const deleteTodo = async (req, res) => {
  try {
    const result = await Todo.deleteOne({ _id: req.params.todoID });
    if (result.deletedCount === 0) {
      return res.status(404).json({ message: "Todo not found" });
    }
    return res.json({ message: "Todo Deleted" });
  } catch (err) {
    return res.status(400).json(err);
  }
};

module.exports = deleteTodo;
