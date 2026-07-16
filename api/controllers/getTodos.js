const Todo = require("../model/schema");

const getTodos = async (req, res) => {
  try {
    const todos = await Todo.find();
    res.json(todos);
  } catch (err) {
    res.status(500).json(err);
  }
};

module.exports = getTodos;
