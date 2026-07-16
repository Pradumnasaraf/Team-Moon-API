const Todo = require("../model/schema");

const updateTodo = async (req, res) => {
  try {
    const todo = await Todo.findOneAndUpdate(
      { _id: req.params.todoID },
      {
        $set: {
          title: req.body.title,
          description: req.body.description,
        },
      },
      { returnDocument: "after" },
    );
    if (!todo) {
      return res.status(404).json({ message: "Todo not found" });
    }
    return res.json(todo);
  } catch (err) {
    return res.status(400).json(err);
  }
};

module.exports = updateTodo;
