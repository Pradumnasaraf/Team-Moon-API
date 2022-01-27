const Todo = require('../model/schema')

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
        (err, Todo) => {
            if (err) {
                res.send(err);
            } else res.json(Todo);
        }
    );
};

module.exports = updateTodo