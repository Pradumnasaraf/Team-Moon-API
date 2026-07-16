const Todo = require("../model/schema");

const updateTodo = (req, res) => {
  Todo.findOneAndUpdate(
    { _id: req.params.todoID },
    {
      $set: {
        title: req.body.title,
        description: req.body.description,
      },
    },
    { new: true },
    (err, todo) => {
      if (err) {
        res.send(err);
      } else res.json(todo);
    }
  );
};

module.exports = updateTodo;
