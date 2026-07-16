const Todo = require("../model/schema");

const postTodo = async (req, res) => {
  try {
    const newTodo = new Todo({
      title: req.body.title,
      description: req.body.description,
    });

    const todo = await newTodo.save();
    res.status(201).json(todo);
  } catch (err) {
    res.status(400).json(err);
  }
};

module.exports = postTodo;
