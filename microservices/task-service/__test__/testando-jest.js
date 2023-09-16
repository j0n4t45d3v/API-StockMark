import "jest"

function sum(x, y) {
	return x + y;
}

describe('testando', () => {
	test('should sum values' , () => {
		expect(sum(1, 2)).toEquals(3);
	})
})
