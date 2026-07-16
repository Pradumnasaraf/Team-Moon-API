const Todo = require("../model/schema");

const getTodo = async (req, res) => {
  try {
    const todo = await Todo.findById(req.params.todoID);
    if (!todo) {
      return res.status(404).json({ message: "Todo not found" });
    }
    return res.json(todo);
  } catch (err) {
    return res.status(500).json(err);
  }
};

module.exports = getTodo;
