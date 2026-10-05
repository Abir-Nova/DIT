def float_extraction_from_float(num):
    print(f"The number is: {num}")
    int_part, float_part = num.split(".")
    print(f"The decimal part is: {float_part}")

# 1
float_extraction_from_float("12.24")
# 2
float_extraction_from_float("424242.8412")




