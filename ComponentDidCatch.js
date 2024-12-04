class MyComponent extends React.Component {
  componentDidCatch(error, info) {
    console.log('Error caught:', error);
    console.log('Error info:', info);
  }

  render() {
    return <h1>Hello, world!</h1>;
  }
}
