Problem 1:

adt UglyArray
	uses Any, Integer, Boolean
	defines UglyArray<T: Any>
	operations
		new: Integer -/-> UglyArray<T>
		get: UglyArray<T> x Integer -/-> T
		put: UglyArray<T> x Integer x T -/-> UglyArray<T>
		length: UglyArray<T> ---> Integer
		empty: UglyArray<T> x Integer -/-> Boolean
	preconditions
		new(n): 0 < n
		get(a, i): 0 <= i < length(a)
		get(a, i): empty(a, i) = false
		put(a, i, t): 0 <= i < length(a)
		empty(a, i): 0 <= i < length(a)
	axioms
		get(new(n), i) = None ????
		empty(new(n), i) = True
		get(put(a, i, t), j) = (if i = j, then t, else get(a, j))
		length(new(n, t)) = n
		length(put(a, i, t)) = length(a)

The most basic difference between UglyArray and Array are that there is no default value for the elements of the array. Instead of returning a default value, trying to retrieve a value from an array at a position that is not set will return some implementation specific value indicating it is empty (this can be specific to the type of the array). In a programming language, neither of these is perfect. It would be optimal to have an array that has an initialization for both an empty array and one filled with a specific value.

Problem 2:

adt FlexibleArray
	uses Any, Integer
	defines FlexibleArray<T: Any>
	operations: 
		new: Integer x Integer x T -/> FlexibleArray<T>
		get: FlexibleArray<T> x Integer -/-> T
		put: FlexibleArray<T> x Integer x T -/-> T
		length: FlexibleArray<T> ---> Integer
		lower: FlexibleArray<T> ---> Integer
	predconditions
		new(m, n, t): m <= n
		get(a, i): lower(a) <= i <= lower(a) + length(a)
		put(a, i, t): lower(a) <= i <= lower(a) + length(a)
	axioms
		get(new(m, n, t), i) = t
		get(put(a, i, t), j) = (if i = j then t else get(a, j))
		length(new(m, n, t)) = n - m
		lower(new(m, n, t)) = m
		length(put(a, i, t)) = length(a)
		lower(put(a, i, t)) = lower(a)

The difference between the FlexibleArray and a regular array is simply the ability to specify the bounds the user wants for the array, instad of the bounds being 0 to lenght - 1. FlexibleArray adds an extra layer of confusion to the Array class, and I would perfer to simply have an array. The confusion that FlexibleArray adds is not worth its functionality, which is easily achieved by simply converting the values from the indicies you want to use to a range from 0 to length - 1. All this would entail is subtraction.



