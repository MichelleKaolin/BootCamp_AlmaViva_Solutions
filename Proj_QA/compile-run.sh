SRC_DIR="src"
OUT_DIR="out"
MAIN_CLASS="br.com.almaviva.qa.Main"

echo ""
echo "🔨 Compilando projeto..."
mkdir -p $OUT_DIR

find $SRC_DIR -name "*.java" > sources.txt

javac -encoding UTF-8 -d $OUT_DIR @sources.txt

if [ $? -ne 0 ]; then
    echo "❌ Erro na compilação."
    rm -f sources.txt
    exit 1
fi

rm -f sources.txt
echo "✅ Compilação bem-sucedida!"
echo ""
echo "▶️  Executando..."
echo ""

java -cp $OUT_DIR $MAIN_CLASS
